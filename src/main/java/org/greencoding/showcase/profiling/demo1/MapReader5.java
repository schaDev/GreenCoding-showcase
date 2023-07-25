package org.greencoding.showcase.profiling.demo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapReader5 {

    public Map<String, Long> readMap(String fileName) throws IOException {
        Map<String, Long> map = new ConcurrentHashMap<>(10_000_000);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (String line; (line = br.readLine()) != null; ) {
                int sep = line.indexOf(':');
                String key = trim(line, 0, sep);
                String value = trim(line, sep + 1, line.length());
                map.put(key, Long.parseLong(value));
            }
        }

        return map;
    }

    private String trim(String line, int from, int to) {
        while (from < to && line.charAt(from) <= ' ') {
            from++;
        }
        while (to > from && line.charAt(to - 1) <= ' ') {
            to--;
        }
        return line.substring(from, to);
    }
}
