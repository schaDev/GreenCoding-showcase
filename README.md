[![Java](https://img.shields.io/badge/Made%20with-Java-orange)](https://openjdk.java.net)

This projects project contains showcases on GreenCoding in java

# What is GreenCoding?

an initiative to create software creating less carbon emissions

# Showcases

The project consists of two different **types** of showcases.

1) Measurement of software carbon emissions
2) Good (and bad) GreenCoding practices

## General Notes

The measuring examples are (currently only) designed for Windows machines running on Intel CPU only and rely on Intel's
Power Gadget [Intel Power Gadget](https://www.intel.com/content/www/us/en/developer/articles/tool/power-gadget.html).
Please install it first to get the measuring showcases running. The examples could be executed by calling GET Request (
e.g in browser).

The result will look similar to this.

```json
{
  "co2": {
    "1        times": "0,000002 CO2(g)",
    "10       times": "0,000019 CO2(g)",
    "100     times": "0,000194 CO2(g)",
    "1.000    times": "0,001942 CO2(g)",
    "10.000   times": "0,019421 CO2(g)",
    "100.000 times": "0,194212 CO2(g)"
  },
  "times": 1,
  "cumulativeEnergyMwh": "0.004004377417090408",
  "cumulativeEnergyJoule": "0.014415762311856333",
  "averagePower": "12.825902",
  "elapsedTime": "0.373580",
  "cpuEnergyMwh": "0.003167836678514516",
  "dramEnergyMwh": "5.218191713693626E-4",
  "gpuEnergyMwh": "1.831340330557368E-5",
  "avgCpuFrequency": "2.112"
}
```

### Notes on calculation methodology

1) The energy consumption of CPU, GPU and DRAM is measured using Intel Power Gadget during code execution (in Joule and
   milli watt hours [mwh])
2) It observes the total CPU utilization and the JVM CPU utilization of this process
3) It applies the ratio of `total CPU utilization / process CPU Utilization` to measured energy consumption of 1)
4) It multiplies the measured energy with local carbon grid emissions per kwh. This can be configured by setting
   property

```
greencoding.emissionsPerKwh=485
```

or programmatically

```       
EnergyResult.setAvgCo2PerKwh(485);
```

Default is set
to [Germany](https://www.umweltbundesamt.de/themen/klima-energie/energieversorgung/strom-waermeversorgung-in-zahlen#Strommix)
**485gCo2e/kwh**

5) The measured Co2-equivalent emissions are linear extrapolated with factors 10/100/1.000/10.000/100.000

## Showcase 1: Carbon impact of Avro and Json Serialization in comparison

### Purpose

simulate and measure the serialization and deserialization of documents in Avro and in Json format to see the
difference.

### Execution

Start the Spring Boot application (`GreenCodingShowcaseApp`) and execute

```
GET http://localhost:8080/serialize/json
or
GET http://localhost:8080/serialize/avro
```

The following request parameters are supported

- `includeFilesystem`: specify if the data should be serialized from and to disk (=true) or from and to memory (
  =false) (Default:false)
- `times`: specify how often the simulation should be executed (default=1)

> ℹ️ the data for this serialization is located in `src/main/resources/serialization-testdata.json`. In Json its size is **6,16KB** and in Avro **2,37KB**

## Showcase 2: Carbon impact of SSL

### Purpose

simulate and measure a ssl handshake and first request encryptions on server and decryption on client side with an 777KB
sized index.html.

### Execution

Start the Spring Boot application (`GreenCodingShowcaseApp`) and execute

```
GET http://localhost:8080/ssl
```

The following request parameters are supported

- `rsaKeyLength`: specify the key length of rsa keypair (default=1024)
- `aesKeyLength`: specify the key length of symmetric AES key (default=128)
- `times`: specify how often the simulation should be executed (default=1)

## Showcase 3: Test your knowledge on GreenCoding

### Purpose

Learn by example the impact of memory leaks and bad object creations on performance, energy and carbon emissions.

### Execution

Open the Unittests in class `GreenCodingTests` and execute them. After observing the bad behaviour try to fix them.

## Showcase 4: Opimize a file import using a profiler (step-by-step)

### Purpose

Learn to use a profiler and optimize some simple Java Code to improve performance and carbon intensity of a simple file import.   

### Execution

A file with 10 million can be generated using `MapGenerator` utility class. Afterwards, run the `MapReaderTests` in package `profiling.demo1`
To understand how and why this is implemented use a profiler (for example build in IntelliJ).

# Troubleshooting
Most likely you should have a look at `src/main/resources/application.propreties` and check Power Gadget Properties are
configured correctly


