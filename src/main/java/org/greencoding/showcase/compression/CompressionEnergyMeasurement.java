package org.greencoding.showcase.compression;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.greencoding.showcase.energy.EnergyMonitor;
import org.greencoding.showcase.energy.EnergyResult;
import org.greencoding.showcase.serialization.avro.AvroSerializer;
import org.greencoding.showcase.serialization.json.JsonSerializer;
import org.greencoding.showcase.serialization.json.testdata.TestdataLoader;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class CompressionEnergyMeasurement {


    private final EnergyMonitor energyMonitor;
    private final GzipImpl gzip;
    private final SnappyImpl snappy;
    private final Lz4Impl lz4;

    private final JsonSerializer jsonSerializer;
    private final AvroSerializer avroSerializer;
    private final TestdataLoader testdataLoader;

    Random r = new Random();
    byte[] contentToCompress;

    public CompressionEnergyMeasurement(EnergyMonitor energyMonitor, GzipImpl gzip, SnappyImpl snappy, Lz4Impl lz4, JsonSerializer jsonSerializer, AvroSerializer avroSerializer, TestdataLoader testdataLoader) {
        this.energyMonitor = energyMonitor;

        this.gzip = gzip;
        this.snappy = snappy;
        this.lz4 = lz4;
        this.jsonSerializer = jsonSerializer;
        this.avroSerializer = avroSerializer;
        this.testdataLoader = testdataLoader;
    }


    @SneakyThrows
    public EnergyResult measureGzip(int contentSizeInKb, int times) {
        float compressionRate = 0f;
        int unzippedSize = 0;
        int zippedSize = 0;
        if (contentToCompress == null || contentToCompress.length != contentSizeInKb * 1024) {
            contentToCompress = createByteArrayOfSize(contentSizeInKb);
        }


        energyMonitor.startRecoring();
        log.info("Recording started for gzip - {} times...", times);

        EnergyResult result;
        try {
            for (int i = 0; i < times; i++) {
                byte[] zippedContent = gzip.compress(contentToCompress);
                if (compressionRate == 0) {
                    unzippedSize = contentToCompress.length;
                    zippedSize = zippedContent.length;
                    compressionRate = (((float) unzippedSize / zippedSize) - 1) * 100;
                }
                gzip.uncompress(zippedContent, contentToCompress.length);
            }

            log.info("compression {} gzip finished.", times);
        } finally {
            result = energyMonitor.stopRecording();
            result.setTimes(times);
        }
        logResultToConsole(times, compressionRate, unzippedSize, zippedSize, result);
        return result;
    }

    @SneakyThrows
    public EnergyResult measureSnappy(int contentSizeInKb, int times) {
        float compressionRate = 0f;
        int unzippedSize = 0;
        int zippedSize = 0;
        if (contentToCompress == null || contentToCompress.length != contentSizeInKb * 1024) {
            contentToCompress = createByteArrayOfSize(contentSizeInKb);
        }


        energyMonitor.startRecoring();
        log.info("Recording started for snappy - {} times...", times);

        EnergyResult result;
        try {
            for (int i = 0; i < times; i++) {
                byte[] zippedContent = snappy.compress(contentToCompress);
                if (compressionRate == 0) {
                    unzippedSize = contentToCompress.length;
                    zippedSize = zippedContent.length;
                    compressionRate = (((float) unzippedSize / zippedSize) - 1) * 100;
                }
                snappy.uncompress(zippedContent, contentToCompress.length);
            }

            log.info("compression {} snappy finished.", times);
        } finally {
            result = energyMonitor.stopRecording();
            result.setTimes(times);
        }
        logResultToConsole(times, compressionRate, unzippedSize, zippedSize, result);
        return result;
    }

    @SneakyThrows
    public EnergyResult measureLz4(int contentSizeInKb, int times) {
        float compressionRate = 0f;
        int unzippedSize = 0;
        int zippedSize = 0;
        if (contentToCompress == null || contentToCompress.length != contentSizeInKb * 1024) {
            contentToCompress = createByteArrayOfSize(contentSizeInKb);
        }


        energyMonitor.startRecoring();
        log.info("Recording started for lz4 - {} times...", times);

        EnergyResult result;
        try {
            for (int i = 0; i < times; i++) {
                byte[] zippedContent = lz4.compress(contentToCompress);
                if (compressionRate == 0) {
                    unzippedSize = contentToCompress.length;
                    zippedSize = zippedContent.length;
                    compressionRate = (((float) unzippedSize / zippedSize) - 1) * 100;
                }
                lz4.uncompress(zippedContent, contentToCompress.length);
            }

            log.info("compression {} lz4 finished.", times);
        } finally {
            result = energyMonitor.stopRecording();
            result.setTimes(times);
        }
        logResultToConsole(times, compressionRate, unzippedSize, zippedSize, result);
        return result;
    }

    private byte[] createByteArrayOfSize(int contentSizeInKb) {
        log.trace("creating random byte array of size {}KB...", contentSizeInKb / 1024);
        contentToCompress = new byte[contentSizeInKb * 1024];
        for (int i = 0; i < contentSizeInKb * 1024; i++) {
            contentToCompress[i] = (byte) (r.nextInt(127));
        }

//        contentToCompress="tmse smoe long medsaddddddddddddklökdölsak kvlödskf dkfodsif mdsfg po48593 ut 903jjroewuf 43 5jflidw 7t4dfs fdösfifkdäkfodgj tü95tjlkguap98tß43tldsiuf04385vkföäflkdslkf ölkg86bßdsasg1 234 ;".getBytes(StandardCharsets.UTF_8);


//        contentToCompress = jsonSerializer.serialize(testdataLoader.loadTestdataPojo()).getBytes(StandardCharsets.UTF_8);

        contentToCompress = avroSerializer.serialize(testdataLoader.loadTestdataAvro());

        log.debug("Random byte array of size {}KB created", contentToCompress.length / 1024);
        return contentToCompress;
    }

    private void logResultToConsole(int times, float compressionRate, int unzippedSize, int zippedSize, EnergyResult result) {
        log.info("\n\tunzipped size:\t\t {} bytes\n" +
                "\tzipped size:\t\t {} bytes\n" +
                "\tcompression rate:\t {}%\n" +
                "\ttimes:\t\t\t\t {}\n" +
                "\tconsumed time:\t\t {} seconds\n" +
                "\tconsumed energy\t\t {} mWh\n" +
                "\tconsumed Co2\t\t {} Co2(g)", unzippedSize, zippedSize, compressionRate, times, result.getElapsedTime(), result.getCumulativeEnergyMwh(), result.getCumulativeCo2());
    }


}
