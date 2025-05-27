package de.codeblocksmc.codelib.api.chat;

public class ChatUtil {
    public static String getColorCode(int level) {
        if (level < 10) return "§f";
        else if (level < 20) return "§2";
        else if (level < 30) return "§9";
        else if (level < 40) return "§5";
        else if (level < 50) return "§b";
        else if (level < 60) return "§a";
        else if (level < 70) return "§3";
        else if (level < 80) return "§d";
        else if (level < 90) return "§e";
        else if (level < 100) return "§c";
        else return "§4";
    }

    public static String getColorCodeHex(int level) {
        return getHexForMC(getColorCode(level).replace("§", ""));
    }

    private static String getHexForMC(String mc) {
        switch (mc) {
            case "f" -> {
                return "FFFFFF";
            }
            case "d" -> {
                return "FF55FF";
            }
            case "5" -> {
                return "AA00AA";
            }
            case "9" -> {
                return "5555FF";
            }
            case "b" -> {
                return "55FFFF";
            }
            case "3" -> {
                return "00AAAA";
            }
            case "a" -> {
                return "55FF55";
            }
            case "e" -> {
                return "FFFF55";
            }
            case "6" -> {
                return "FFAA00";
            }
            case "c" -> {
                return "FF5555";
            }
            case "4" -> {
                return "AA0000";
            }
            case "2" -> {
                return "00AA00";
            }
        }
        return "FFFFFF";
    }
}