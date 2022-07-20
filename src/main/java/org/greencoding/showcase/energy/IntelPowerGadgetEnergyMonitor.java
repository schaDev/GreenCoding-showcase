package org.greencoding.showcase.energy;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

@Component
@Slf4j
public class IntelPowerGadgetEnergyMonitor implements EnergyMonitor {


    private final String powerGadgetExecutable;


    private final String powerGadgetresultDir;


    private long startTime;
    private int recoverTrials;

    private double sumProcessCpuRatio;
    private int countProcessCpuRatio;
    private boolean shouldMonitorCpuUsage;

    public IntelPowerGadgetEnergyMonitor(@Value("${powergadget.executable}") String powerGadgetExecutable, @Value("${powergadget.resultdir}") String powerGadgetresultDir) {
        this.powerGadgetExecutable = powerGadgetExecutable;
        this.powerGadgetresultDir = powerGadgetresultDir;
        if (!Files.exists(Path.of(powerGadgetExecutable))) {
            throw new IllegalArgumentException("Intel power gadget executable was not found. Please configure 'powergadget.executable' property correctly, current: " + powerGadgetExecutable);
        }
    }

    @SneakyThrows
    @Override
    public synchronized void startRecoring() {
        startTime = System.currentTimeMillis();
        if (!checkProcessRunning("IntelPowerGadget.exe")) {
            log.info("Intel Power Gadget not yet running. Starting...");
            Runtime.getRuntime().exec(powerGadgetExecutable);
            Thread.sleep(1500);
        }

        if (checkProcessRunning("IntelPowerGadget.exe")) {
            log.info("starting energy recording");
            Runtime.getRuntime().exec(powerGadgetExecutable + " -start");
            sumProcessCpuRatio = 0;
            countProcessCpuRatio = 0;
            shouldMonitorCpuUsage = true;
            new Thread(new AvgCpuMonitorRunnable()).start();
        } else {
            throw new IllegalStateException("IntelPowerGadget could not be started.");
        }

    }

    @SneakyThrows
    private boolean checkProcessRunning(String processToFind) {
        String filenameFilter = "/nh /fi \"Imagename eq " + processToFind + "\"";
        String tasksCmd = System.getenv("windir") + "/system32/tasklist.exe " + filenameFilter;

        Process p = Runtime.getRuntime().exec(tasksCmd);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

        ArrayList<String> procs = new ArrayList<>();
        String line = null;
        while ((line = input.readLine()) != null)
            procs.add(line);

        input.close();

        return procs.stream().filter(row -> row.indexOf(processToFind) > -1).count() > 0;
    }


    @SneakyThrows
    private EnergyResult interpretResultFile(double ratio) {
        File file = getLatestFile();
        int lastLines = 19;
        int counter = 0;

        EnergyResult result = new EnergyResult();

        try (ReversedLinesFileReader reader = new ReversedLinesFileReader(file, Charset.defaultCharset())) {
            while (counter < lastLines) {
                String line = reader.readLine();
                if (line.contains("Cumulative Processor Energy_0 (mWh)")) {
                    result.setCumulativeEnergyMwh(String.valueOf(Double.valueOf(shorten(line)) * ratio));
                } else if (line.contains("Cumulative Processor Energy_0 (Joules)")) {
                    result.setCumulativeEnergyJoule(String.valueOf(Double.valueOf(shorten(line)) * ratio));
                } else if (line.contains("Average Processor Power_0")) {
                    result.setAveragePower(shorten(line));
                } else if (line.contains("Cumulative IA Energy_0 (mWh)")) {
                    result.setCpuEnergyMwh(String.valueOf(Double.valueOf(shorten(line)) * ratio));
                } else if (line.contains("Cumulative DRAM Energy_0 (mWh)")) {
                    result.setDramEnergyMwh(String.valueOf(Double.valueOf(shorten(line)) * ratio));
                } else if (line.contains("Cumulative GT Energy_0 (mWh")) {
                    result.setGpuEnergyMwh(String.valueOf(Double.valueOf(shorten(line)) * ratio));
                } else if (line.contains("Total Elapsed Time")) {
                    result.setElapsedTime(shorten(line));
                } else if (line.contains("Measured RDTSC Frequency")) {
                    result.setAvgCpuFrequency(shorten(line));
                }
                counter++;
            }
            log.info("energy recording stopped. Total consumption was {} mWh / {} Joule.", result.getCumulativeEnergyMwh(), result.getCumulativeEnergyJoule());
            return result;
        }
    }

    private String shorten(final String line) {
        String data = line.substring(line.indexOf('(')).replace(" = ", "");
        return data.substring(data.indexOf(")") + 1);
    }

    @SneakyThrows
    private File getLatestFile() {
        log.trace("interpreting file...");
        File directory = new File(powerGadgetresultDir);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }
        if (recoverTrials > 31) {
            log.warn("call power gadget stop again");
            Runtime.getRuntime().exec(powerGadgetExecutable + " -stop");
            throw new IllegalStateException("File with energy data was not found in time. Maybe you called top before start? PLEASE ENSURE TO STOP POWERGADGET MANUALLY");
        }

        if (chosenFile != null && chosenFile.lastModified() < startTime) {
            Thread.sleep(200);
            recoverTrials++;
            log.trace("detected file {} is atfer start date. waiting 200ms...", chosenFile.getAbsolutePath());
            if (recoverTrials % 10 == 0) {
                log.info("energy file not found, calling power gadget stop again");
                Runtime.getRuntime().exec(powerGadgetExecutable + " -stop");
            }
            chosenFile = getLatestFile();
        }
        if(chosenFile== null){
            throw new IllegalArgumentException("Intel Power Gadget result file was not found. Maybe you have configured property 'powergadget.resultdir' wrong? currently configured: "+powerGadgetresultDir);
        }
        return chosenFile;
    }

    @SneakyThrows
    @Override
    public synchronized EnergyResult stopRecording() {
        recoverTrials = 0;
        log.trace("stopping energy recording...");
        Runtime.getRuntime().exec(powerGadgetExecutable + " -stop");
        shouldMonitorCpuUsage = false;
        Thread.sleep(1500);
        log.info("relative cpu ratio {}", sumProcessCpuRatio / countProcessCpuRatio);
        return interpretResultFile(sumProcessCpuRatio / countProcessCpuRatio);
    }

    private class AvgCpuMonitorRunnable implements Runnable {

        com.sun.management.OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean();


        @Override
        @SneakyThrows
        public void run() {

            while (shouldMonitorCpuUsage) {
                double processLoad = bean.getProcessCpuLoad();
                double systemLoad = bean.getSystemCpuLoad();
                if (systemLoad != 0) {
                    sumProcessCpuRatio = (sumProcessCpuRatio + (processLoad / systemLoad));
                    countProcessCpuRatio++;
                }
                Thread.sleep(100);
            }
        }
    }
}
