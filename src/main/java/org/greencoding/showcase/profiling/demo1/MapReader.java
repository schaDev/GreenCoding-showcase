package org.greencoding.showcase.profiling.demo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * inspired by async profiler sample cases
 * https://github.com/apangin/java-profiling-presentation/tree/master/src/demo7
 */

public class MapReader {

    public Map<String, Long> readMap(String fileName) throws IOException {
        Map<String, Long> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (String line; (line = br.readLine()) != null; ) {
                String[] kv = line.split(":", 2);
                String key = kv[0].trim();
                String value = kv[1].trim();
                map.put(key, Long.parseLong(value));
            }
        }
        return map;
    }
}
