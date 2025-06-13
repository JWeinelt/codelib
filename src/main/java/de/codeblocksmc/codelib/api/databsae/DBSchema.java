package de.codeblocksmc.codelib.api.databsae;

import java.util.ArrayList;
import java.util.List;

public class DBSchema {
    private final String name;
    private List<DBTable> tables = new ArrayList<>();

    public DBSchema(String name) {
        this.name = name;
    }

    public void registerTable(DBTable table) {
        tables.add(table);
    }
}
