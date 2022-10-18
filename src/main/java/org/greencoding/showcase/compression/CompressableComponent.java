package org.greencoding.showcase.compression;

public interface CompressableComponent {
    byte[] uncompress(byte[] compressedContent, int targetUncompressedLength);

    byte[] compress(byte[] content);
}
