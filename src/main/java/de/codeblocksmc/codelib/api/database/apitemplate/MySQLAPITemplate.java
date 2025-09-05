package de.codeblocksmc.codelib.api.database.apitemplate;

import de.codeblocksmc.codelib.api.database.DBSchema;
import de.codeblocksmc.codelib.api.database.DBTable;
import de.codeblocksmc.codelib.api.database.StorageProvider;
import de.codeblocksmc.codelib.api.database.action.TableModifier;
import de.codeblocksmc.codelib.api.database.template.StorageTemplate;

public class MySQLAPITemplate extends StorageTemplate {
    protected MySQLAPITemplate() {
        super(StorageProvider.MYSQL);
    }

    @Override
    public void connect() {

    }

    @Override
    public void checkConnection() {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void afterConnection() {

    }

    @Override
    public DBSchema getSchema(String name) {
        return null;
    }

    @Override
    public DBTable getTable(String schema, String name) {
        return null;
    }

    @Override
    public void modifyTable(TableModifier modifier) {

    }

    @Override
    public void deleteTable(DBSchema schema, String name) {

    }

    @Override
    public void deleteSchema(String name) {

    }
}
