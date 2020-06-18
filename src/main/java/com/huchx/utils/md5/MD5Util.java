package com.huchx.utils.md5;


import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public enum  MD5Util {
    INSTANCE;

    /**
     * 对 String 进行HMAC-md5运算
     *
     * @param message 要进行HMAC-md5运算的数据 × @param str1 HMAC-md5密钥
     * @param key
     * @return 返回签名后的 byte 数组十六进制签名
     */

    public String generateDigest(String message, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec keySpec = new SecretKeySpec(
                key,
                "HmacMD5");

        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(keySpec);
        byte[] rawHmac = mac.doFinal(message.getBytes(Charset.defaultCharset()));

        return Hex.encodeHexString(rawHmac).toUpperCase();
    }

    /**
     * 对 String 进行HMAC-md5运算
     *
     * @param message 要进行HMAC-md5运算的数据 × @param str1 HMAC-md5密钥
     * @param key
     * @return 返回签名后的 byte 数组十六进制签名
     */

    public String generateDigest(String message, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        return generateDigest(message, key.getBytes(Charset.defaultCharset()));
    }

    public String generateDigest16(String message, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
        return this.generateDigest(message, key).substring(8, 24);
    }

    public String generateDigest16(String message, String key) throws InvalidKeyException, NoSuchAlgorithmException {
        return this.generateDigest(message, key).substring(8, 24);
    }

    public String generateKey() {
        byte[] salt = Digests.generateSalt(8);
        return Encodes.encodeHex(salt);
    }

    public static void main(String[] arg) throws NoSuchAlgorithmException, InvalidKeyException {
        String message = "123456";
        String key1 = "ZVdGc2NtOTZiUT09";
        byte[] key = key1.getBytes(Charset.defaultCharset());
        SecretKeySpec keySpec = new SecretKeySpec(
                key,
                "HmacMD5");

        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(keySpec);
        byte[] rawHmac = mac.doFinal(message.getBytes(Charset.defaultCharset()));

        System.out.println((Hex.encodeHexString(rawHmac).toUpperCase().substring(8, 24)));

    }
}
