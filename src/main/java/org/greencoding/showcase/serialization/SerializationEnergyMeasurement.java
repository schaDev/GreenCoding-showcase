package org.greencoding.showcase.serialization;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.greencoding.showcase.energy.EnergyMonitor;
import org.greencoding.showcase.energy.EnergyResult;
import org.greencoding.showcase.serialization.avro.AvroSerializer;
import org.greencoding.showcase.serialization.avro.testdata.LargeExampleAvro;
import org.greencoding.showcase.serialization.json.JsonSerializer;
import org.greencoding.showcase.serialization.json.testdata.LargeExamplePojo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.stream.IntStream;

@Component
@Slf4j
public class SerializationEnergyMeasurement {

    private final EnergyMonitor energyMonitor;

    private final JsonSerializer jsonSerializer;
    private final AvroSerializer avroSerializer;

    public SerializationEnergyMeasurement(EnergyMonitor energyMonitor, JsonSerializer jsonSerializer, AvroSerializer avroSerializer) {
        this.energyMonitor = energyMonitor;
        this.jsonSerializer = jsonSerializer;
        this.avroSerializer = avroSerializer;
        avroSerializer.setUp();
    }


    @SneakyThrows
    public EnergyResult measureSerializeJson(boolean includeFilesystem, int times) {
        // create testdata outside of energy monitoring
        LargeExamplePojo testdataPojo = loadTestdataPojo();

        energyMonitor.startRecoring();
        EnergyResult result;

        try {
            IntStream.rangeClosed(1, times)
                    .forEach(i -> {
                        if (includeFilesystem) {
                            jsonSerializer.serializeToFile(testdataPojo, new File("target/dummy.json"));
                            jsonSerializer.deserializeFromFile(LargeExamplePojo.class, new File("target/dummy.json"));
                        } else {
                            String json = jsonSerializer.serialize(testdataPojo);
                            LargeExamplePojo example = (LargeExamplePojo) jsonSerializer.deserialize(LargeExamplePojo.class, json);
                        }
                    });
        } finally {
            result = energyMonitor.stopRecording();
            result.setTimes(times);
        }
        return result;
    }

    @SneakyThrows
    public EnergyResult measureSerializeAvro(boolean includeFilesystem, int times) {
        // create testdata outside of energy monitoring
        LargeExampleAvro testdataAvro = loadTestdataAvro();

        energyMonitor.startRecoring();
        EnergyResult result;

        try {
            IntStream.rangeClosed(1, times)
                    .forEach(i -> {
                        if (includeFilesystem) {
                            avroSerializer.serializeToFile(testdataAvro, new File("target/dummy.avro"));
                            avroSerializer.deserializeFromFile(new File("target/dummy.avro"));
                        } else {
                            byte[] avroBin = avroSerializer.serialize(testdataAvro);
                            avroSerializer.deserialize(avroBin);
                        }
                    });
        } finally {
            result = energyMonitor.stopRecording();
            result.setTimes(times);
        }
        return result;
    }


    @SneakyThrows
    private LargeExamplePojo loadTestdataPojo() {
        return (LargeExamplePojo) jsonSerializer.deserializeFromFile(LargeExamplePojo.class, new ClassPathResource("serialization-testdata.json").getFile());
    }

    @SneakyThrows
    private LargeExampleAvro loadTestdataAvro() {
        return (LargeExampleAvro) jsonSerializer.deserializeFromFile(LargeExampleAvro.class, new ClassPathResource("serialization-testdata.json").getFile());
    }
}
