package org.greencoding.showcase.serialization.avro;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.greencoding.showcase.serialization.avro.testdata.LargeExampleAvro;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

@Slf4j
@Component
public class AvroSerializer {

    private boolean inited;
    private ByteArrayOutputStream baos;

    private DatumReader<LargeExampleAvro> reader;
    private DatumWriter<LargeExampleAvro> writer;

    @SneakyThrows
    public void setUp() {
        this.reader = new SpecificDatumReader<>(LargeExampleAvro.getClassSchema());
        this.writer = new SpecificDatumWriter<>(LargeExampleAvro.getClassSchema());
        this.baos = new ByteArrayOutputStream();
        this.inited = true;
    }


    @SneakyThrows
    public void serializeToFile(LargeExampleAvro dummy, File file) {
        if (!inited)
            throw new IllegalStateException("Serializer must be initialized");

        this.baos.reset();
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(this.baos, null);

        writer.write(dummy, encoder);
        encoder.flush();
        this.baos.flush();

        try (FileOutputStream fos = new FileOutputStream(file)) {
            baos.writeTo(fos);
        }
    }

    @SneakyThrows
    public byte[] serialize(LargeExampleAvro dummy) {
        if (!inited)
            throw new IllegalStateException("Serializer must be initialized");

        this.baos.reset();
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(this.baos, null);

        writer.write(dummy, encoder);
        encoder.flush();
        this.baos.flush();

        return baos.toByteArray();
    }

    @SneakyThrows
    public Object deserializeFromFile(File file) {
        byte[] msg = Files.readAllBytes(file.toPath());
        BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(msg, null);

        return reader.read(null, decoder);
    }

    @SneakyThrows
    public Object deserialize(byte[] bytes) {
        byte[] msg = bytes;
        BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(msg, null);

        return reader.read(null, decoder);
    }
}
