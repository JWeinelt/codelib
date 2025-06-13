package de.codeblocksmc.codelib.api.database;

import java.util.HashMap;

public class TableColumn {
    private ColumnType type;
    private int length;
    private HashMap<Integer, Object> values = new HashMap<>();
}
