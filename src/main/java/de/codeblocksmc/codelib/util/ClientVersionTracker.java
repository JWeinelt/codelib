package de.codeblocksmc.codelib.util;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ClientVersionTracker {

    private static final Map<UUID, Integer> protocolVersions = new ConcurrentHashMap<>();

    public static void setProtocolVersion(UUID uuid, int version) {
        protocolVersions.put(uuid, version);
    }

    public static int getProtocolVersion(UUID uuid) {
        return protocolVersions.getOrDefault(uuid, -1); // -1 = unbekannt
    }


    /**
     * Maps all protocols version (starting with 1.7) to their corresponding human-readable update id.
     * @param version Protocol version (e.g. 47 means "1.8.9"
     * @return The Update id, e.g. "1.21.3" or "1.8.9"
     */
    public static String getFriendlyName(int version) {
        return switch (version) {
            case 4 -> "1.7.5";
            case 5 -> "1.7.10";
            case 47 -> "1.8.9";
            case 107 -> "1.9";
            case 108 -> "1.9.1";
            case 109 -> "1.9.2";
            case 110 -> "1.9.4";
            case 210 -> "1.10.2";
            case 315 -> "1.11";
            case 316 -> "1.11.2";
            case 335 -> "1.12";
            case 338 -> "1.12.1";
            case 340 -> "1.12.2";
            case 393 -> "1.13";
            case 401 -> "1.13.1";
            case 404 -> "1.13.2";
            case 477 -> "1.14";
            case 480 -> "1.14.1";
            case 485 -> "1.14.2";
            case 490 -> "1.14.3";
            case 498 -> "1.14.4";
            case 573 -> "1.15";
            case 575 -> "1.15.1";
            case 578 -> "1.15.2";
            case 735 -> "1.16";
            case 736 -> "1.16.1";
            case 751 -> "1.16.2";
            case 753 -> "1.16.3";
            case 754 -> "1.16.5";
            case 755 -> "1.17";
            case 756 -> "1.17.1";
            case 757 -> "1.18.1";
            case 758 -> "1.18.2";
            case 759 -> "1.19";
            case 760 -> "1.19.2";
            case 761 -> "1.19.3";
            case 762 -> "1.19.4";
            case 763 -> "1.20.1";
            case 764 -> "1.20.2";
            case 765 -> "1.20.4";
            case 766 -> "1.20.6";
            case 767 -> "1.21.1";
            case 768 -> "1.21.3";
            case 769 -> "1.21.4";
            case 770 -> "1.21.5";
            case 771 -> "1.21.6";
            case 772 -> "1.21.7";
            default -> "Unknown";
        };

    }
}
