package com.amd.util;

import com.amd.constants.CommonConstants;
import com.amd.dto.RSAKeys;
import com.amd.enums.Encryption;

import javax.crypto.Cipher;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class RSAUtils {

    private static final Base64.Encoder base64Encoder = Base64.getEncoder();

    private static final Base64.Decoder base64Decoder = Base64.getDecoder();

    private static final RSAKeys RSA_KEYS;

    static {
        try {
            RSA_KEYS = generateKey("RSA", CommonConstants.KEY_SIZE);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static RSAKeys generateKey(String encryption, int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(encryption);
        generator.initialize(keySize);
        KeyPair keyPair = generator.generateKeyPair();
        return new RSAKeys(keyPair.getPrivate(), keyPair.getPublic());
    }

    public static String encryptWithRSA(String valueToEnc) {
        String encryptedValue = null;
        try {
            Cipher cipher = Cipher.getInstance(Encryption.RSA.getName());
            cipher.init(Cipher.ENCRYPT_MODE, RSA_KEYS.getPublicKey());
            encryptedValue = base64Encoder.encodeToString(cipher.doFinal(valueToEnc.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error during RSA Encryption");
            throw new RuntimeException(e);
        }
        return encryptedValue;
    }

    public static String decryptWithRSA(String key) {
        String decryptedValue = null;
        try {
            Cipher cipher = Cipher.getInstance(Encryption.RSA.getName());
            cipher.init(Cipher.DECRYPT_MODE, RSA_KEYS.getPrivateKey());
            decryptedValue = new String(cipher.doFinal(base64Decoder.decode(key)));
        } catch (Exception e) {
            System.out.println("Error during RSA Decryption");
            throw new RuntimeException(e);
        }
        return decryptedValue;
    }

    public static void writeRSAKeyToFile(RSAKeys rsaKeys) {
        String privateKey = base64Encoder.encodeToString(rsaKeys.getPrivateKey().getEncoded()).trim();
        String privateKeyFileName = CommonConstants.BASE_OUTPUT_PATH + CommonConstants.PRIVATE_PREFIX + CommonConstants.TASK1_OUTPUT;
        String publicKey = base64Encoder.encodeToString(rsaKeys.getPublicKey().getEncoded()).trim();
        String publicKeyFileName = CommonConstants.BASE_OUTPUT_PATH + CommonConstants.PUBLIC_PREFIX + CommonConstants.TASK1_OUTPUT;
        FileUtils.writeToFile(privateKey.getBytes(StandardCharsets.UTF_8), privateKeyFileName);
        FileUtils.writeToFile(publicKey.getBytes(StandardCharsets.UTF_8), publicKeyFileName);
    }
}