package org.greencoding.showcase.encryption;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

@Component
public class EncryptionSupport {

    public static final String ALG_RSA = "RSA";
    public static final String ALG_AES = "AES";

    @SneakyThrows
    public KeyPair generateRsaKeyPair(int rsaKeyLength) {
        if (rsaKeyLength == 0) {
            return null;
        }
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALG_RSA);
        generator.initialize(rsaKeyLength);
        return generator.generateKeyPair();
    }

    @SneakyThrows
    public SecretKey generateAesKey(int aesKeyLength) {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALG_AES);
        keyGenerator.init(aesKeyLength);
        return keyGenerator.generateKey();
    }

    @SneakyThrows
    public void encryptFile(SecretKey aesKey,
                            File inputFile, File outputFile) {
        String algorithm = "AES/CBC/PKCS5Padding";
        byte[] ivb = new byte[16];
        new SecureRandom().nextBytes(ivb);
        IvParameterSpec iv = new IvParameterSpec(ivb);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, iv);
        processFile(inputFile, outputFile, cipher);
    }


    @SneakyThrows
    public void decryptFile(SecretKey aesKey,
                            File inputFile, File outputFile) {
        String algorithm = "AES/CBC/PKCS5Padding";
        byte[] ivb = new byte[16];
        new SecureRandom().nextBytes(ivb);
        IvParameterSpec iv = new IvParameterSpec(ivb);

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, aesKey, iv);
        processFile(inputFile, outputFile, cipher);
    }

    private void processFile(File inputFile, File outputFile, Cipher cipher) throws IOException, IllegalBlockSizeException, BadPaddingException {
        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            byte[] buffer = new byte[64];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) {
                    outputStream.write(output);
                }
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                outputStream.write(outputBytes);
            }
        }
    }


    @SneakyThrows
    public String decrypt(KeyPair pair, byte[] encryptedMessageBytes) {
        Cipher decryptCipher = Cipher.getInstance(ALG_RSA);
        decryptCipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());

        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        return new String(decryptedMessageBytes, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public byte[] encrypt(KeyPair pair, String secretMessage) {
        Cipher encryptCipher = Cipher.getInstance(ALG_RSA);
        encryptCipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());

        byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
        return encryptCipher.doFinal(secretMessageBytes);
    }
}
