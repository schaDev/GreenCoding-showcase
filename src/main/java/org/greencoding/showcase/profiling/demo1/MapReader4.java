package org.greencoding.showcase.profiling.demo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapReader4 {


    public Map<String, Long> readMap(String fileName) throws IOException {
        Map<String, Long> map = new HashMap<>(10_000_000);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (String line; (line = br.readLine()) != null; ) {
                int sep = line.indexOf(':');
                String key = trim(line, 0, sep); // own trim method, calling substring once
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
