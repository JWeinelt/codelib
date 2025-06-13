package de.codeblocksmc.codelib.api.database;

import lombok.Getter;

import java.util.Date;
import java.util.HashMap;

public class TableColumn {
    @Getter
    private final ColumnType type;
    @Getter
    private final int length;
    @Getter
    private final HashMap<Integer, Object> values;

    public TableColumn(ColumnType type, int length, HashMap<Integer, Object> values) {
        this.type = type;
        this.length = length;
        this.values = values;
    }

    public Object getValueRaw(int row) {
        return values.getOrDefault(row, null);
    }

    public String getValueString(int row) {
        return (String) values.getOrDefault(row, null);
    }

    public int getValueInt(int row) {
        return (int) values.getOrDefault(row, null);
    }

    public double getValueDouble(int row) {
        return (double) values.getOrDefault(row, null);
    }
    public float getValueFloat(int row) {
        return (float) values.getOrDefault(row, null);
    }
    public char getValueChar(int row) {
        return (char) values.getOrDefault(row, null);
    }
    public boolean getValueBoolean(int row) {
        return (boolean) values.getOrDefault(row, null);
    }
    public Date getValueDate(int row) {
        return (Date) values.getOrDefault(row, null);
    }
}
