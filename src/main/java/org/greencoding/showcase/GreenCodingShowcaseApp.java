package org.greencoding.showcase;

import lombok.extern.slf4j.Slf4j;
import org.greencoding.showcase.energy.EnergyResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class GreenCodingShowcaseApp {

    // Default: Germany 2021: 485 https://www.umweltbundesamt.de/themen/klima-energie/energieversorgung/strom-waermeversorgung-in-zahlen#Strommix
    @Value("${greencoding.emissionsPerKwh:485}")
    int emissionsPerKwh;

    public static void main(String[] args) {
        SpringApplication.run(GreenCodingShowcaseApp.class, args);
        // Germany 2021: 485 https://www.umweltbundesamt.de/themen/klima-energie/energieversorgung/strom-waermeversorgung-in-zahlen#Strommix
        EnergyResult.setAvgGramOfCo2PerKwh(485);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        log.info("grid emissions for calculations configured to {} gCo2e/kwh", emissionsPerKwh);
        EnergyResult.setAvgGramOfCo2PerKwh(emissionsPerKwh);
    }

}
