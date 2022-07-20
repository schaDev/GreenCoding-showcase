package org.greencoding.showcase.fibonacci;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class FibonacciImpl {

    private HashMap<Integer, Long> cache = new HashMap<>();

    public static long fib(int n) {
        long a = 0;
        long b = 1;
        for (int i = 0; i < n; i++) {
            long bOld = b;
            b = a + b;
            a = bOld;
        }
        return a;
    }

    public static long fibShort(int n) {
        long a = 0, b = 1;
        for (int i = 0; i < n; i++) b = a + (a = b);
        return a;
    }


    public long fibRecursive(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibRecursive(n - 1) + fibRecursive(n - 2);
        }
    }

    public long fibRecursiveCached(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        if (n == 0) {
            cache.clear();
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            cache.put(n, fibRecursiveCached(n - 1) + fibRecursiveCached (n - 2));
            return cache.get(n);
        }
    }
}
