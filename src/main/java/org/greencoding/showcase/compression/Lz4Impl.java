package org.greencoding.showcase.compression;

import lombok.SneakyThrows;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;
import org.springframework.stereotype.Component;

@Component
public class Lz4Impl implements CompressableComponent {

    LZ4Factory factory = LZ4Factory.fastestInstance();


    @Override
    @SneakyThrows
    public byte[] uncompress(byte[] compressedContent, int targetUncompressedLength) {

//        byte[] restored = new byte[300 * 1024];
//        LZ4SafeDecompressor decompressor2 = factory.safeDecompressor();
//        decompressor2.decompress(compressedContent, 0, compressedContent.length, restored, 0);
//        return restored;

        LZ4FastDecompressor decompressor = factory.fastDecompressor();
        byte[] restored = new byte[targetUncompressedLength];
        decompressor.decompress(compressedContent, restored);
        return restored;

    }

    @Override
    @SneakyThrows
    public byte[] compress(byte[] content) {
        LZ4Compressor compressor = factory.fastCompressor();
        int maxCompressedLength = compressor.maxCompressedLength(content.length);
        byte[] compressed = new byte[maxCompressedLength];
        compressor.compress(content, 0, content.length, compressed, 0, maxCompressedLength);

        return compressed;


    }
}
