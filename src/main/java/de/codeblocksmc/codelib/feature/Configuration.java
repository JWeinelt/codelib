package de.codeblocksmc.codelib.feature;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Configuration {
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private final String _INFO_1 = "Define the maximum amount of players that can connect to a single server instance using the server connector.";
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private final String _INFO_4 = "Typically, this should be set to the maximum player amount of your sub-servers minus a small buffer (e.g. 5 players).";
    private int connectorMaxPlayers = 30;

    private boolean useHDB = false;
    private boolean usePartyAndFriends = false;
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private final String _INFO_2 = "This will enable plugin integration features automatically if a supported plugin is found. (recommended)";
    private boolean autoDetectPluginSpecificFeatures = true;
    private boolean autoDetectCaesar = true;
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private final String _INFO_3 = "Define if CodeLib should work with Caesar's data to improve server connections. (recommended)";
    private boolean useCaesarAPI = true;

    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private final String _INFO_5 = "Define if CodeLib should check for updates automatically";
    private boolean autoUpdateChecker = true;

    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private final String _INFO_DONT_TOUCH = "DO NOT CHANGE THESE VALUES!";
    private final String codeLibVersion = "4.1.0";
    private final String configVersion = "1.2";
    private final String buildNumber = "2025-09-05";
}