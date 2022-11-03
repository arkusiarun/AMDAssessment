package com.amd.util;

import com.amd.constants.CommonConstants;
import com.amd.enums.Encryption;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AESUtils {

    public static void encrypt(String sourceFile, String outputFile, String token) {
        try {
            Cipher cipher = getCipher();
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(token, true), new IvParameterSpec(new byte[16]));
            byte[] bytes = FileUtils.readFromFile(Paths.get(sourceFile));
            FileUtils.writeToFile(cipher.doFinal(bytes), outputFile);
        } catch (Exception e) {
            System.out.println("Error during AES Encrypting");
            throw new RuntimeException(e);
        }
    }

    private static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(Encryption.AES.getName());
    }

    public static void decrypt(String inputFile, String outPutFile, String token) {
        try {
            Cipher cipher = getCipher();
            cipher.init(Cipher.DECRYPT_MODE, generateKey(token, false), new IvParameterSpec(new byte[16]));
            byte[] bytes = FileUtils.readFromFile(Paths.get(inputFile));
            FileUtils.writeToFile(cipher.doFinal(bytes), outPutFile);
        } catch (Exception e) {
            System.out.println("Error during AES Decryption");
            throw new RuntimeException(e);
        }
    }

    private static Key generateKey(String token, boolean write) {
        if (write) {
            encryptAndWriteKey(token);
        }
        byte[] keyValue = token.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyValue, "AES");
    }

    public static void encryptAndWriteKey(String token) {
        String encryptedKey = RSAUtils.encryptWithRSA(token);
        FileUtils.writeToFile(encryptedKey.getBytes(StandardCharsets.UTF_8), CommonConstants.BASE_OUTPUT_PATH + CommonConstants.AES_KEY_FILE);
    }
}