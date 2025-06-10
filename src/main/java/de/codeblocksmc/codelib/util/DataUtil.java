package de.codeblocksmc.codelib.util;

import java.io.InputStream;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {
    public static boolean isValidInt(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ignored) {return false;}
    }

    public static boolean isValidIntList(String a) {
        List<Integer> is = new ArrayList<>();
        for (String s : a.split(",")) {
            if (!isValidInt(s)) return false;
        }
        return true;
    }

    public static List<Integer> convertDest(String argument) {
        List<Integer> is = new ArrayList<>();
        for (String s : argument.split(",")) {
            if (isValidInt(s)) is.add(Integer.parseInt(s));
        }
        return is;
    }

    public static String checkHashURL(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            InputStream is = new URL(input).openStream();

            try {
                is = new DigestInputStream(is, md);

                int b;

                while ((b = is.read()) > 0) {
                    ;
                }
            } finally {
                is.close();
            }
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();

            for (byte b : digest) {
                sb.append(
                        Integer.toString((b & 0xff) + 0x100, 16).substring(
                                1));
            }
            return sb.toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
