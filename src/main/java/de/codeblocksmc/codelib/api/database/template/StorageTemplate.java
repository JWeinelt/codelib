package de.codeblocksmc.codelib.api.database.template;

import de.codeblocksmc.codelib.api.database.DBSchema;
import de.codeblocksmc.codelib.api.database.DBTable;
import de.codeblocksmc.codelib.api.database.StorageProvider;
import lombok.Getter;

import java.sql.Connection;

public abstract class StorageTemplate {
    // The connection object to the database
    public Connection conn;

    @Getter
    private final StorageProvider provider;

    protected StorageTemplate(StorageProvider provider) {
        this.provider = provider;
    }

    public abstract void connect();
    public abstract void checkConnection();
    public abstract void disconnect();

    public abstract void afterConnection();

    public abstract DBSchema getSchema(String name);
    public abstract DBTable getTable(String schema, String name);

    public DBTable getTable(DBSchema schema, String name) {

    }
}
