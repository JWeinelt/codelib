package de.codeblocksmc.codelib.api.databsae;

import lombok.Builder;

@Builder
public class DBLibConfig {
    private StorageProvider provider;
    private String host;
    private int port;
    private String username;
    private String password;
    private String databaseName;

}