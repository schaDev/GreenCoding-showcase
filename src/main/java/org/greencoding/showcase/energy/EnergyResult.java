package org.greencoding.showcase.energy;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnergyResult {

    public static void setAvgGramOfCo2PerKwh(int avgCo2PerKwh) {
        AVG_G_CO2_PER_KWH = avgCo2PerKwh;
    }

    // Germany 2021: 485 https://www.umweltbundesamt.de/themen/klima-energie/energieversorgung/strom-waermeversorgung-in-zahlen#Strommix
    public static int AVG_G_CO2_PER_KWH = 485;

    public Map<String, String> getCo2() {
        Map<String, String> map = new LinkedHashMap<>();
        String number = cumulativeEnergyMwh;
        map.put(String.format("%,d", times) + "        times", String.format("%." + 6 + "f", Double.valueOf(Double.valueOf(number) / 1000 / 1000 * AVG_G_CO2_PER_KWH)) + " CO2(g)");
        map.put(String.format("%,d", times * 10) + "       times", String.format("%." + 6 + "f", 10 * Double.valueOf(Double.valueOf(number) / 1000 / 1000 * AVG_G_CO2_PER_KWH)) + " CO2(g)");
        map.put(String.format("%,d", times * 100) + "     times", String.format("%." + 6 + "f", 100 * Double.valueOf(Double.valueOf(number) / 1000 / 1000 * AVG_G_CO2_PER_KWH)) + " CO2(g)");
        map.put(String.format("%,d", times * 1000) + "    times", String.format("%." + 6 + "f", 1000 * Double.valueOf(Double.valueOf(number) / 1000 / 1000 * AVG_G_CO2_PER_KWH)) + " CO2(g)");
        map.put(String.format("%,d", times * 10000) + "   times", String.format("%." + 6 + "f", 10000 * Double.valueOf(Double.valueOf(number) / 1000 / 1000 * AVG_G_CO2_PER_KWH)) + " CO2(g)");
        map.put(String.format("%,d", times * 100000) + " times", String.format("%." + 6 + "f", 100000 * Double.valueOf(Double.valueOf(number) / 1000 / 1000 * AVG_G_CO2_PER_KWH)) + " CO2(g)");
        return map;
    }

    public String getCumulativeCo2() {
        return String.format("%." + 6 + "f", Double.valueOf(Double.valueOf(cumulativeEnergyMwh) / 1000 / 1000 * AVG_G_CO2_PER_KWH));
    }


    private String co2;
    private String cumulativeCo2;
    private long times;

    private String cumulativeEnergyMwh;
    private String cumulativeEnergyJoule;
    private String averagePower;

    private String elapsedTime;

    private String cpuEnergyMwh;
    private String dramEnergyMwh;
    private String gpuEnergyMwh;


    private String avgCpuFrequency;

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getCumulativeEnergyMwh() {
        return cumulativeEnergyMwh;
    }

    public String getAveragePower() {
        return averagePower;
    }

    public void setAveragePower(String cumulativePower) {
        this.averagePower = cumulativePower;
    }

    public void setCumulativeEnergyMwh(String cumulativeEnergyMwh) {
        this.cumulativeEnergyMwh = cumulativeEnergyMwh;
    }

    public String getCumulativeEnergyJoule() {
        return cumulativeEnergyJoule;
    }

    public void setCumulativeEnergyJoule(String cumulativeEnergyJoule) {
        this.cumulativeEnergyJoule = cumulativeEnergyJoule;
    }

    public String getAvgCpuFrequency() {
        return avgCpuFrequency;
    }

    public void setAvgCpuFrequency(String avgCpuFrequency) {
        this.avgCpuFrequency = avgCpuFrequency;
    }

    public String getCpuEnergyMwh() {
        return cpuEnergyMwh;
    }

    public void setCpuEnergyMwh(String cpuEnergyMwh) {
        this.cpuEnergyMwh = cpuEnergyMwh;
    }

    public String getGpuEnergyMwh() {
        return gpuEnergyMwh;
    }

    public void setGpuEnergyMwh(String gpuEnergyMwh) {
        this.gpuEnergyMwh = gpuEnergyMwh;
    }

    public String getDramEnergyMwh() {
        return dramEnergyMwh;
    }

    public void setDramEnergyMwh(String dramEnergyMwh) {
        this.dramEnergyMwh = dramEnergyMwh;
    }
}
