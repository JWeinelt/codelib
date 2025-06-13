package de.codeblocksmc.codelib.api.database.template;

import de.codeblocksmc.codelib.api.database.DBSchema;
import de.codeblocksmc.codelib.api.database.DBTable;
import de.codeblocksmc.codelib.api.database.StorageProvider;
import de.codeblocksmc.codelib.api.database.action.TableModifier;
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
    public abstract void modifyTable(TableModifier modifier);
    public abstract void deleteTable(DBSchema schema, String name);
    public abstract void deleteSchema(String name);
}