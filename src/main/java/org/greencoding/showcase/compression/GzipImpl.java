package org.greencoding.showcase.compression;


import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Component
public class GzipImpl implements CompressableComponent {


    @SneakyThrows
    @Override
    public byte[] uncompress(byte[] compressedContent, int targetUncompressedLength) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                compressedContent);
             GZIPInputStream gzipInputStream = new GZIPInputStream(
                     byteArrayInputStream);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ) {


            byte[] buffer = new byte[1024];
            int length;
            while ((length = gzipInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }

    @SneakyThrows
    @Override
    public byte[] compress(byte[] content) {
        // unfortunately not working with AutoClosable, cause toByteArray has to be called after close()
        ByteArrayOutputStream byteStream =
                new ByteArrayOutputStream();
        GZIPOutputStream zipStream = new GZIPOutputStream(byteStream);
        try {
            zipStream.write(content);
        } finally {
            byteStream.close();
            zipStream.close();
        }
        return byteStream.toByteArray();
    }
}
