package com.amd.service.impl;

import com.amd.constants.CommonConstants;
import com.amd.dto.RSAKeys;
import com.amd.enums.Encryption;
import com.amd.service.Action;
import com.amd.util.AESUtils;
import com.amd.util.FileUtils;
import com.amd.util.RSAUtils;
import com.amd.util.SHAUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ActionImpl implements Action {

    private static Path basePath = Paths.get(ActionImpl.class.getResource(CommonConstants.HOME_PATH).getPath());

    /**
     * Execute Task 1.
     * Application to generate an RSA key pair, key size = 2048 bits
     */
    @Override
    public void Task1() {
        try {
            RSAKeys rsaKeys = RSAUtils.generateKey(Encryption.RSA.getName(), CommonConstants.KEY_SIZE);
            RSAUtils.writeRSAKeyToFile(rsaKeys);
        } catch (Exception e) {
            System.out.println("Task 1 Failed");
            e.printStackTrace();
        }
    }

    /**
     * Execute Task 2.
     * Application to generate SHA-256 hash of the attached file.
     */
    @Override
    public void Task2() {
        try {
            Path path = Paths.get(basePath + CommonConstants.HOME_PATH + CommonConstants.IMAGE_FILE_PATH);
            String hexCode = SHAUtils.generateHash(Encryption.SHA.getName(), path);
            FileUtils.writeToFile(hexCode.getBytes(StandardCharsets.UTF_8), CommonConstants.BASE_OUTPUT_PATH + CommonConstants.TASK2_OUTPUT);
        } catch (Exception e) {
            System.out.println("Task 2 Failed");
            e.printStackTrace();
        }
    }

    /**
     * Application that will encrypt and decrypt the file using the RSA key pair generated
     */
    @Override
    public void Task3() {
        try {
            /*
            Source File --> Actual File
             */
            String sourceFile = basePath + CommonConstants.HOME_PATH + CommonConstants.IMAGE_FILE_PATH;

            /*
            Encrypted File --> File Generated Post Encryption
             */
            String encryptedFile = CommonConstants.BASE_OUTPUT_PATH + CommonConstants.ENCRYPTED_FILE;

            /*
            Decrypted File --> Using source as encrypted File Output File
             */
            String decryptedFile = CommonConstants.BASE_OUTPUT_PATH + CommonConstants.DECRYPTED_FILE;

            /*
            Encrypt Source File
             */
            AESUtils.encrypt(sourceFile, encryptedFile, CommonConstants.AES_TOKEN);

            /*
            Read Encrypted Token From File
             */
            Path path = Paths.get(CommonConstants.BASE_OUTPUT_PATH + CommonConstants.AES_KEY_FILE);
            String key = RSAUtils.decryptWithRSA(new String(FileUtils.readFromFile(path)));

            /*
            Decrypt Encrypted File
             */
            AESUtils.decrypt(encryptedFile, decryptedFile, key);

        } catch (Exception e) {
            System.out.println("Task 3 Failed");
            e.printStackTrace();
        }
    }
}