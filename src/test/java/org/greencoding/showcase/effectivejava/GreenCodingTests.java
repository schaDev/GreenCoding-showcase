package org.greencoding.showcase.effectivejava;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(2000L > System.currentTimeMillis() - start,
                "Execution took longer than 2s"); // FIXME solve the performance issue
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

        assertTrue(2000L > System.currentTimeMillis() - start,
                "Execution took longer than 2s"); // FIXME solve the performance issue
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
