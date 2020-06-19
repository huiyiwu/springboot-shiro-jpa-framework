package com.huchx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class,args);
    }

    public static String decrypt(String encryption)
    {
        char[] decryption = new char[encryption.length() / 2];
        int i = 0;
        for (int j = 0; i < encryption.length() / 2; j++)
        {
            if (j == "a90s9d0asoqpnfie4".length()) {
                j = 0;
            }
            char n = (char)Integer.valueOf(encryption.substring(i * 2, i * 2 + 2), 16).intValue();
            decryption[i] = ((char)(n ^ "a90s9d0asoqpnfie4".charAt(j)));i++;
        }
        String decoding = "";
        try
        {
            decoding = new String(String.valueOf(decryption).getBytes("iso-8859-1"), "UTF-8");
        }
        catch (Exception localException) {}
        return decoding;
    }
}
