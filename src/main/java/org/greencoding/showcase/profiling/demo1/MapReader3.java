package org.greencoding.showcase.profiling.demo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapReader3 {


    public Map<String, Long> readMap(String fileName) throws IOException {
        Map<String, Long> map = new HashMap<>(10_000_000);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (String line; (line = br.readLine()) != null; ) {
                int sep = line.indexOf(':'); // use indexOf and substring instead of split
                String key = line.substring(0, sep).trim();
                String value = line.substring(sep + 1).trim();
                map.put(key, Long.parseLong(value));
            }
        }

        return map;
    }
}
