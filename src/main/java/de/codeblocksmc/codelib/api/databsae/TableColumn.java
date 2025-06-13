package de.codeblocksmc.codelib.api.databsae;

import java.util.HashMap;
import java.util.List;

public class TableColumn {
    private ColumnType type;
    private int length;
    private HashMap<Integer, Object> values = new HashMap<>();
}
