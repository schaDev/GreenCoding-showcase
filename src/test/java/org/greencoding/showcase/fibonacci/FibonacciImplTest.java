package org.greencoding.showcase.fibonacci;

import org.greencoding.showcase.energy.EnergyMonitor;
import org.greencoding.showcase.energy.EnergyResult;
import org.greencoding.showcase.energy.IntelPowerGadgetEnergyMonitor;
import org.junit.jupiter.api.Test;

class FibonacciImplTest {

    private FibonacciImpl fibonacci = new FibonacciImpl();

    @Test
    void testFib() {
        System.out.println(fibonacci.fib(50));
        System.out.println(fibonacci.fibShort(50));
//        System.out.println(fibonacci.fibRecursive(50));
        System.out.println(fibonacci.fibRecursiveCached(50));
    }

}