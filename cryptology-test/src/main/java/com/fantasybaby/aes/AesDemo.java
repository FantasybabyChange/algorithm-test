package com.fantasybaby.aes;

import com.google.common.collect.Lists;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionMethod;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * AES with ZIP
 * Created on 7/4/2023.
 *
 * @author Reid Liu
 */
public class AesDemo {
    private static final String content = "sdakjsdklajskldjaljsdkla_asdasdasda_中文_中文_asdasdasdasd_asdasdasd_asdasda";
    private static final String content2 = "sda22222222222222222222kjsdklajskldjaljsdkla_asdasdasda_中文_中文_asdasdasdasd_asdasdasd_asdasda";
    private static final String OUT_PUT_FILE = "D://test_output.zip";

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        SecureRandom secureRandom = new SecureRandom();
//        int keyBitSize = 128;
//        keyGenerator.init(keyBitSize, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] encoded = secretKey.getEncoded();
        String key = Base64.getEncoder().encodeToString(encoded);
        System.out.println(key);
        String encrypt = encrypt(key, content);
        zipFile(key, encrypt);
        System.out.println(encrypt);

        System.out.println(decrypt(key, unzipFile(key)));


        zipOutputStreamFile(key, Lists.newArrayList(content, content2), new FileOutputStream(OUT_PUT_FILE));
        System.out.println("reader input stream" + zipInputStreamFile(key, new FileInputStream(OUT_PUT_FILE)));
    }

    public static String encrypt(String key, String content) {
        byte[] decode = Base64.getDecoder().decode(key);
        SecretKeySpec aes = new SecretKeySpec(Arrays.copyOf(decode, 16), "AES");
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(Cipher.ENCRYPT_MODE, aes);
            byte[] encrypted = instance.doFinal(content.getBytes(StandardCharsets.UTF_8));
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

    private static ZipParameters buildZipParameters(CompressionMethod compressionMethod, boolean encrypt,
                                                    EncryptionMethod encryptionMethod, AesKeyStrength aesKeyStrength) {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionMethod(compressionMethod);
        zipParameters.setEncryptionMethod(encryptionMethod);
        zipParameters.setAesKeyStrength(aesKeyStrength);
        zipParameters.setEncryptFiles(encrypt);
        return zipParameters;
    }

    public static void zipOutputStreamFile(String key, List<String> contents, OutputStream outputStream) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream, key.toCharArray(), StandardCharsets.UTF_8);
        int count = 0;
        for (String content : contents) {
            ZipParameters zipParameters1 = buildZipParameters(CompressionMethod.DEFLATE, true, EncryptionMethod.AES, AesKeyStrength.KEY_STRENGTH_128);
            zipParameters1.setFileNameInZip(String.valueOf(System.currentTimeMillis()) + count);
            zipOutputStream.putNextEntry(zipParameters1);
            zipOutputStream.write(encrypt(key, content).getBytes(StandardCharsets.UTF_8));
            count++;
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
    }

    public static List<String> zipInputStreamFile(String key, InputStream inputStream) throws IOException {
        List<String> results = Lists.newArrayList();
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream, key.toCharArray(), StandardCharsets.UTF_8)) {
            while (zipInputStream.getNextEntry() != null) {
                results.add(decrypt(key, IOUtils.toString(zipInputStream, StandardCharsets.UTF_8)));
            }

        }
        return results;
    }

    public static String unzipFile(String key) throws IOException {
        File file = new File("D://test.zip");
        ZipFile zipFile = new ZipFile(file, key.toCharArray());
        FileHeader fileHeader = zipFile.getFileHeader("test1");
        return IOUtils.toString(zipFile.getInputStream(fileHeader), StandardCharsets.UTF_8);


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
