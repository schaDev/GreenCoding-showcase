package org.greencoding.showcase.encryption;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.greencoding.showcase.energy.EnergyMonitor;
import org.greencoding.showcase.energy.EnergyResult;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.io.File;
import java.security.KeyPair;
import java.util.Base64;
import java.util.stream.IntStream;

@Service
@Slf4j
public class EncryptionEnergyMeasurement {

    private static final String INDEX_HTML = "tagesschau.index.html";
    private final File encryptedFile = new File("target/file.encrypted.txt");
    private final File decryptedFile = new File("target/file.decrypted.txt");

    private final EnergyMonitor energyMonitor;
    private final EncryptionSupport encryptionSupport;

    public EncryptionEnergyMeasurement(EnergyMonitor energyMonitor, EncryptionSupport encryptionSupport) {
        this.energyMonitor = energyMonitor;
        this.encryptionSupport = encryptionSupport;
    }


    @SneakyThrows
    public EnergyResult measureSsl(int rsaKeyLength, int aesKeyLength, int times) {

        // generate rsa key pair once
        KeyPair rsaKeyPair = encryptionSupport.generateRsaKeyPair(rsaKeyLength);


        Resource resource = new ClassPathResource(INDEX_HTML);
        File indexHtml = resource.getFile();

        energyMonitor.startRecoring();
        log.info("Recording started for rsaKeyLength={}, aesKeyLength={} - {} times...", rsaKeyLength, aesKeyLength, times);

        EnergyResult result;
        try {
            // disable encryption by setting rsaKeyLength to 0
            if (rsaKeyLength != 0) {
                IntStream.rangeClosed(1, times)
                        .forEach(i -> {
                            // generate AES key
                            SecretKey aesKey = encryptionSupport.generateAesKey(aesKeyLength);
                            // asymmetric RSA
                            byte[] encryptedAesKey = encryptionSupport.encrypt(rsaKeyPair, Base64.getEncoder().encodeToString(aesKey.getEncoded()));
                            // symmetric AES
                            encryptionSupport.encryptFile(aesKey, indexHtml, encryptedFile);
                            // asymmetric RSA
                            encryptionSupport.decrypt(rsaKeyPair, encryptedAesKey);
                            // symmetric AES
                            encryptionSupport.decryptFile(aesKey, encryptedFile, decryptedFile);
                        });
            }
            log.info("simulating {} ssl encryptions finished.", times);
        } finally {
            result = energyMonitor.stopRecording();
            result.setTimes(times);
        }
        return result;
    }

}
