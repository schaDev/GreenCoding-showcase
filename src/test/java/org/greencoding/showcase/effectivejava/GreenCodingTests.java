package org.greencoding.showcase.effectivejava;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class GreenCodingTests {

    // Who can spot the GreenCoding Bugs?
    // Let the tests succeed without modifying any assertion!
    // Hint: inspired by Joshua Bloch, Effective Java 3rd Edition

    @Test
    void stringsAtScale1() {
        long start = System.currentTimeMillis();

        // START: actual code
        String a = "";
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            a = new String("GreenCoding");
        }
        // END: actual code

        assertEquals("GreenCoding", a); // Don't change this line
        assertTrue(3000L > System.currentTimeMillis() - start,
                "Execution took longer than 3s"); // FIXME solve the performance issue
    }

    @Test
    void stringsAtScale2() {
        long start = System.currentTimeMillis();

        // START: actual code
        String a = "";
        for (long i = 0; i <= 50000; i++) {
            a = a + "GreenCoding";
        }
        // END: actual code
        assertTrue(1000L > System.currentTimeMillis() - start,
                "Execution took longer than 1s"); // FIXME solve the performance issue
    }

    @Test
    void sum() {
        long start = System.currentTimeMillis();

        // START: actual code
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        // END: actual code

        assertEquals(2305843008139952128L, sum); // Don't change this line
        assertTrue(2000L > System.currentTimeMillis() - start,
                "Execution took longer than 2s"); // FIXME solve the performance issue
    }

    @Test
    void loop() {
        List<String> someList = initHugeList();

        // FOR LOOP
        long startLoop1 = System.currentTimeMillis();
        int size = someList.size();
        for (int i = 0; i < size; i++) {
            String tmp = someList.get(i);
        }
        long timeLoop1 = System.currentTimeMillis() - startLoop1;

        // FOREACH LOOP
        long startLoop2 = System.currentTimeMillis();
        for (String s : someList) {
            String tmp = s;
        }
        long timeLoop2 = System.currentTimeMillis() - startLoop2;

        // LAMBDA LOOP
        long startLoop3 = System.currentTimeMillis();
        someList.forEach(s -> {
            String tmp = s;
        });
        long timeLoop3 = System.currentTimeMillis() - startLoop3;

        // WHILE LOOP
        long startLoop4 = System.currentTimeMillis();
        int i = someList.size();
        while (i > 0) {
            i--;
            String tmp = someList.get(i);
        }
        long timeLoop4 = System.currentTimeMillis() - startLoop4;


        log.info("\n FOR LOOP:\t\t {}\n FOREACH LOOP:\t {}\n LAMBDA LOOP:\t {}\n WHILE LOOP:\t {}", timeLoop1, timeLoop2, timeLoop3, timeLoop4);
        assertTrue(timeLoop1 < timeLoop4);
    }

    private static List<String> initHugeList() {
        int size = 400000000;
        String gc = "GreenCoding";
        List<String> someList = new ArrayList<>(size);
        for (int l = 0; l <= size; l++) {
            someList.add(gc);
        }
        return someList;
    }

    @Test
    void stack() throws InterruptedException {
        int times = 10000000;
        printUsedMemory();
        Stack stack = new Stack(times);
        for (int i = 0; i < times; i++) {
            stack.push(new Object());
        }
        assertEquals(times, stack.size); // Don't change this line
        long usedMemAfterPush = printUsedMemory();
        for (int i = 0; i < times; i++) {
            stack.pop();
        }
        long usedMemAfterPop = printUsedMemory();
        assertEquals(0, stack.size); // Don't change this line
        assertTrue(usedMemAfterPop < usedMemAfterPush,
                "Heap memory is expected to be cleaned up, after pop -> MemLeak"); // FIXME spot the Memory Leak and fix it
    }


    private static class Stack {
        private final Object[] elements;
        private int size = 0;

        public Stack(int capacity) {
            elements = new Object[capacity];
        }

        public void push(Object e) {
            elements[size++] = e;
        }

        public Object pop() {
            if (size == 0)
                throw new EmptyStackException();
            return elements[--size];
        }
    }

    private long printUsedMemory() throws InterruptedException {
        // Yes, calling gc() and sleep() is performance bug as well but not the one you should find here.
        // It is just called to highlight the actual memory leak
        System.gc();
        Thread.sleep(1000l);
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memBean.getHeapMemoryUsage();
        System.out.println("used heap memory: " + heapMemoryUsage.getUsed() / 1024 / 1024 + "MB");
        return heapMemoryUsage.getUsed();

    }
}
