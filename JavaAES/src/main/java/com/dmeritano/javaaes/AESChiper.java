package com.dmeritano.javaaes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public abstract class AESChiper {

    static private final String ENCODING = "UTF-8";
    static private final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    static private final String CIPHER_ALGORITHM = "AES";

    public static String encrypt(String textPlain, String key) {
        String msgEncrypted = "Error Encrypting";
        byte[] msgEncryptedByte;
        byte[] keyByte;
        Cipher chiper;
        SecretKeySpec secretKeySpec;
        IvParameterSpec ivParameterSpec;
        try {
            msgEncryptedByte = textPlain.getBytes(ENCODING);
            keyByte = getKeyBytes(key);
        } catch (NullPointerException | UnsupportedEncodingException e) {
            msgEncrypted += ": " + e.getMessage();
            System.out.println(msgEncrypted);
            return msgEncrypted;
        }

        secretKeySpec = new SecretKeySpec(keyByte, CIPHER_ALGORITHM);
        ivParameterSpec = new IvParameterSpec(keyByte);

        try {
            chiper = Cipher.getInstance(TRANSFORMATION);
            chiper.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            msgEncryptedByte = chiper.doFinal(msgEncryptedByte);
            msgEncrypted = Base64.getEncoder().encodeToString(msgEncryptedByte);
            
            return msgEncrypted;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            msgEncrypted += ": " + e.getMessage();
            System.out.println(msgEncrypted);
            return msgEncrypted;
        }
    }

    public static String decrypt(String textEncrypted, String key) {
        String msgDecrypted = "Error Decrypting";
        byte[] msgEncryptedByte;
        byte[] keyByte;
        try {
            msgEncryptedByte = Base64.getDecoder().decode(textEncrypted);//Base64.decodeBase64(textEncrypted.getBytes("UTF8"));
            
            keyByte = getKeyBytes(key);
        } catch (NullPointerException | IllegalArgumentException e) {
            msgDecrypted += ": " + e.getMessage();
            System.out.println(msgDecrypted);
            return msgDecrypted;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyByte, CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(keyByte);
        try {
            Cipher chiper = Cipher.getInstance(TRANSFORMATION);
            chiper.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            msgEncryptedByte = chiper.doFinal(msgEncryptedByte);
            msgDecrypted = new String(msgEncryptedByte, ENCODING);
            return msgDecrypted;
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            msgDecrypted += ": " + e.getMessage();
            System.out.println(msgDecrypted);
            return msgDecrypted;
        }
    }
    
    private static byte[] getKeyBytes(String key) {
        byte[] keyBytes = new byte[16];
        try {
            byte[] parameterKeyBytes = key.getBytes(ENCODING);
            System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
        } catch (UnsupportedEncodingException e) {
            System.out.println("[Error][AES][getKeyBytes][0]: " + e.getMessage());
        }
        return keyBytes;
    }
}