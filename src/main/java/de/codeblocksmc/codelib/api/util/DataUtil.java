package de.codeblocksmc.codelib.api.util;

import java.io.InputStream;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {
    /**
     * Check if String is a valid Integer
     * @param s String to check
     * @return {@code true} if valid Integer, {@code false} if not
     */
    public static boolean isValidInt(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ignored) {return false;}
    }

    /**
     * Check if String is a valid List of Integers
     * @param a String to check
     * @return {@code true} if valid List of Integers, {@code false} if not
     */
    public static boolean isValidIntList(String a) {
        List<Integer> is = new ArrayList<>();
        for (String s : a.split(",")) {
            if (!isValidInt(s)) return false;
        }
        return true;
    }

    /**
     * Convert String to List of Integers
     * @param argument String to convert
     * @return List of Integers
     */
    public static List<Integer> convertDest(String argument) {
        List<Integer> is = new ArrayList<>();
        for (String s : argument.split(",")) {
            if (isValidInt(s)) is.add(Integer.parseInt(s));
        }
        return is;
    }

    /**
     * Get MD5 hash of a URL
     * @param input URL to check
     * @return MD5 hash of the URL
     */
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
