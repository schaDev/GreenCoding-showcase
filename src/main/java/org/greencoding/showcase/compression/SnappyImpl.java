package org.greencoding.showcase.compression;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.xerial.snappy.Snappy;

@Component
public class SnappyImpl implements CompressableComponent {


    @Override
    @SneakyThrows
    public byte[] uncompress(byte[] compressedContent, int targetUncompressedLength) {
        return Snappy.uncompress(compressedContent);
    }

    @Override
    @SneakyThrows
    public byte[] compress(byte[] content) {
        return Snappy.compress(content);
    }
}
