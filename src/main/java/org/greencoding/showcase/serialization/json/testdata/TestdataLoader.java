package org.greencoding.showcase.serialization.json.testdata;

import lombok.SneakyThrows;
import org.greencoding.showcase.serialization.avro.testdata.LargeExampleAvro;
import org.greencoding.showcase.serialization.json.JsonSerializer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class TestdataLoader {

    private final JsonSerializer jsonSerializer;

    public TestdataLoader(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    @SneakyThrows
    public LargeExamplePojo loadTestdataPojo() {
        return (LargeExamplePojo) jsonSerializer.deserializeFromFile(LargeExamplePojo.class, new ClassPathResource("serialization-testdata.json").getFile());
    }

    @SneakyThrows
    public LargeExampleAvro loadTestdataAvro() {
        return (LargeExampleAvro) jsonSerializer.deserializeFromFile(LargeExampleAvro.class, new ClassPathResource("serialization-testdata.json").getFile());
    }
}
