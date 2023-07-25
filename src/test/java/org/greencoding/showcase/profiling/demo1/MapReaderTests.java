package org.greencoding.showcase.profiling.demo1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

class MapReaderTests {

    @Test
    void testMapReader1() throws IOException {
        MapReader mapReader = new MapReader();
        long startTime = System.nanoTime();
        Map<String, Long> map = mapReader.readMap("in.txt");
        long time = System.nanoTime() - startTime;

        System.out.printf("v1: Imported map in %.3f seconds\n", time / 1e9);
        Assertions.assertTrue(map.size() > 1);
    }

    @Test
    void testMapReader2() throws IOException {
        MapReader2 mapReader = new MapReader2();
        long startTime = System.nanoTime();
        Map<String, Long> map = mapReader.readMap("in.txt");
        long time = System.nanoTime() - startTime;

        System.out.printf("v2: Imported map in %.3f seconds\n", time / 1e9);
        Assertions.assertTrue(map.size() > 1);
    }

    @Test
    void testMapReader3() throws IOException {
        MapReader3 mapReader = new MapReader3();
        long startTime = System.nanoTime();
        Map<String, Long> map = mapReader.readMap("in.txt");
        long time = System.nanoTime() - startTime;

        System.out.printf("v3: Imported map in %.3f seconds\n", time / 1e9);
        Assertions.assertTrue(map.size() > 1);
    }

    @Test
    void testMapReader4() throws IOException {
        MapReader4 mapReader = new MapReader4();
        long startTime = System.nanoTime();
        Map<String, Long> map = mapReader.readMap("in.txt");
        long time = System.nanoTime() - startTime;

        System.out.printf("v4: Imported map in %.3f seconds\n", time / 1e9);
        Assertions.assertTrue(map.size() > 1);
    }

    @Test
    void testMapReader5() throws IOException {
        MapReader5 mapReader = new MapReader5();
        long startTime = System.nanoTime();
        Map<String, Long> map = mapReader.readMap("in.txt");
        long time = System.nanoTime() - startTime;

        System.out.printf("v5: Imported map in %.3f seconds\n", time / 1e9);
        Assertions.assertTrue(map.size() > 1);
    }

}