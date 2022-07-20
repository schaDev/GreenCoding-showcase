package org.greencoding.showcase.fibonacci;

import lombok.extern.slf4j.Slf4j;
import org.greencoding.showcase.energy.EnergyMonitor;
import org.greencoding.showcase.energy.EnergyResult;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@Slf4j
public class FibonacciEnergyMeasurement {

    private final FibonacciImpl fibonacci;
    private final EnergyMonitor energyMonitor;


    public FibonacciEnergyMeasurement(FibonacciImpl fibonacci, EnergyMonitor energyMonitor) {
        this.fibonacci = fibonacci;
        this.energyMonitor = energyMonitor;
    }

    public EnergyResult measureFibRecurisive(int number, int times) {
        log.info("starting measurement for fibonacci recursive implementation times={}...", times);

        energyMonitor.startRecoring();
        EnergyResult result;

        try {
            IntStream.rangeClosed(1, times)
                    .forEach(i -> fibonacci.fibRecursive(number));
        } finally {
            result = energyMonitor.stopRecording();
            result.setTimes(times);
        }

        return result;
    }

    public EnergyResult measureFibRecurisiveCached(int number, int times) {
        log.info("starting measurement for fibonacci recursive implementation times={}...", times);

        energyMonitor.startRecoring();
        EnergyResult result;

        try {
            IntStream.rangeClosed(1, times)
                    .forEach(i -> fibonacci.fibRecursiveCached(number));
        } finally {
            result = energyMonitor.stopRecording();
            result.setTimes(times);
        }

        return result;
    }
}
