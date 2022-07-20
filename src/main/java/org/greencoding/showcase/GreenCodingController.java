package org.greencoding.showcase;

import lombok.extern.slf4j.Slf4j;
import org.greencoding.showcase.encryption.EncryptionEnergyMeasurement;
import org.greencoding.showcase.energy.EnergyResult;
import org.greencoding.showcase.serialization.SerializationEnergyMeasurement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class GreenCodingController {

    private final EncryptionEnergyMeasurement encryptionService;

    private final SerializationEnergyMeasurement serializationEnergyMeasurement;

    public GreenCodingController(EncryptionEnergyMeasurement encryptionService, SerializationEnergyMeasurement serializationEnergyMeasurement) {
        this.encryptionService = encryptionService;
        this.serializationEnergyMeasurement = serializationEnergyMeasurement;
    }

    @GetMapping("/ssl")
    public EnergyResult ssl(
            @RequestParam(defaultValue = "1024") int rsaKeyLength,
            @RequestParam(defaultValue = "128") int aesKeyLength,
            @RequestParam(defaultValue = "1") int times) {

        return encryptionService.measureSsl(rsaKeyLength, aesKeyLength, times);
    }

    @GetMapping("/serialize/json")
    public EnergyResult serializeJson(
            @RequestParam(defaultValue = "1") int times, @RequestParam(defaultValue = "false") boolean includeFilesystem) {

        return serializationEnergyMeasurement.measureSerializeJson(includeFilesystem, times);
    }

    @GetMapping("/serialize/avro")
    public EnergyResult serializeAvro(
            @RequestParam(defaultValue = "1") int times, @RequestParam(defaultValue = "false") boolean includeFilesystem) {

        return serializationEnergyMeasurement.measureSerializeAvro(includeFilesystem, times);
    }


}
