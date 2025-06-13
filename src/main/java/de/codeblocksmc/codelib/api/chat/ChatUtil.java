package de.codeblocksmc.codelib.api.chat;

/**
 * Utility class for working with Minecraft color codes based on player levels.
 * <p>
 * This class provides methods to retrieve Minecraft formatting codes and their corresponding
 * hexadecimal representations for use in chat messages, scoreboards, GUIs, and more.
 */
public class ChatUtil {

    /**
     * Returns the legacy Minecraft color code (§x) associated with the given level.
     * <p>
     * Color mapping is defined in level ranges:
     * <ul>
     *     <li>{@code <10} → §f (white)</li>
     *     <li>{@code <20} → §2 (dark green)</li>
     *     <li>{@code <30} → §9 (blue)</li>
     *     <li>{@code <40} → §5 (dark purple)</li>
     *     <li>{@code <50} → §b (aqua)</li>
     *     <li>{@code <60} → §a (green)</li>
     *     <li>{@code <70} → §3 (dark aqua)</li>
     *     <li>{@code <80} → §d (light purple)</li>
     *     <li>{@code <90} → §e (yellow)</li>
     *     <li>{@code <100} → §c (red)</li>
     *     <li>{@code ≥100} → §4 (dark red)</li>
     * </ul>
     *
     * @param level the player level
     * @return the Minecraft color code (e.g. §a)
     */
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

    /**
     * Returns the hexadecimal color code equivalent of the legacy Minecraft color code
     * for a given level.
     * <p>
     * Internally uses {@link #getColorCode(int)} and converts it to a 6-digit hex code
     * suitable for MiniMessage or Adventure API components.
     *
     * @param level the player level
     * @return a 6-character hex color string (e.g. {@code FFFFFF})
     */
    public static String getColorCodeHex(int level) {
        return getHexForMC(getColorCode(level).replace("§", ""));
    }

    /**
     * Converts a single-character legacy Minecraft color code (without §) to a 6-digit
     * hexadecimal color string.
     * <p>
     * For example, {@code "a"} becomes {@code "55FF55"}.
     *
     * @param mc the legacy color character (e.g. {@code "a", "c", "4"})
     * @return the corresponding 6-digit hex color code (e.g. {@code "FF5555"})
     */
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
