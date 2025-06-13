package de.codeblocksmc.codelib.api.database;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DBLibConfig {
    private StorageProvider provider;
    private String host;
    private int port;
    private String username;
    private String password;
    private String databaseName;

}