package com.sogo.myglide.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    public static String md5(File f) {

        FileInputStream fis = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(f);
            // 100KB each time
            byte[] buffer = new byte[102400];
            int length;
            long loopCount = 0;
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
                loopCount++;
            }
            md.digest();
            byte[] value = md.digest();
            if (value != null) {
                return covertBytesToHexString(value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * use utf-8 to encode text as bytes
     *
     * @param text
     * @return
     */
    public static byte[] md5(String text) {
        return md5(text, "utf-8");
    }

    public static String md5Hex(String text) {
        return md5Hex(text, "utf-8");
    }

    public static String md5Hex(String text, String enc) {
        byte[] value = md5(text, enc);
        if (value != null) {
            return covertBytesToHexString(value);
        }
        return "";
    }

    public static byte[] md5(String url, String enc) {
        if (url == null)
            return null;
        try {
            byte[] outputBytes = null;
            byte[] inputBytes = url.getBytes(enc);
            MessageDigest questionDigest = MessageDigest.getInstance("MD5");
            questionDigest.update(inputBytes);
            outputBytes = questionDigest.digest();
            return outputBytes;
        } catch (Exception e) {
            return null;
        }
    }

    private static char[] hexchars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String covertBytesToHexString(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        char[] buf = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; ++i) {
            byte b = bytes[i];
            buf[i * 2] = hexchars[b >>> 4 & 0xf];
            buf[i * 2 + 1] = hexchars[b & 0xf];
        }
        return new String(buf);
    }
}
