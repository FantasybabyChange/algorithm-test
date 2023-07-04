package com.fantasybaby.aes;

import com.google.common.base.Charsets;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created on 7/4/2023.
 *
 * @author Reid Liu
 */
public class AesDemo {
    private static final String content = "sdakjsdklajskldjaljsdkla_asdasdasda_中文_中文_asdasdasdasd_asdasdasd_asdasda";

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        int keyBitSize = 128;
        keyGenerator.init(keyBitSize, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] encoded = secretKey.getEncoded();
        String key = Base64.getEncoder().encodeToString(encoded);
        System.out.println(key);
        String encrypt = encrypt(key);
        zipFile(key, encrypt);
        System.out.println(encrypt);

        System.out.println(decrypt(key, unzipFile(key)));

    }

    public static String encrypt(String key) {
        byte[] decode = Base64.getDecoder().decode(key);
        SecretKeySpec aes = new SecretKeySpec(Arrays.copyOf(decode, 16), "AES");
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(Cipher.ENCRYPT_MODE, aes);
            byte[] encrypted = instance.doFinal(content.getBytes("utf-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void zipFile(String key, String content) throws IOException {
        File file = new File("D://test.zip");
        file.createNewFile();
        ZipFile zipFile = new ZipFile(file, key.toCharArray());
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setFileNameInZip("test1");
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        zipFile.addStream(inputStream, zipParameters);
        zipFile.close();
    }

    public static String unzipFile(String key) throws IOException {
        File file = new File("D://test.zip");
        ZipFile zipFile = new ZipFile(file, key.toCharArray());
        FileHeader fileHeader = zipFile.getFileHeader("test1");
        return  IOUtils.toString(zipFile.getInputStream(fileHeader), StandardCharsets.UTF_8);



    }

    public static String decrypt(String key, String encrypt) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(Arrays.copyOf(Base64.getDecoder().decode(key), 16), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encrypt)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
