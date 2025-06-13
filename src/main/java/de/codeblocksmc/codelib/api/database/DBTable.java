package de.codeblocksmc.codelib.api.database;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DBTable {
    @Getter
    private List<TableColumn> columns = new ArrayList<>();

    public void columns(Consumer<TableColumn> consumer) {
        for (TableColumn column : columns) {
            consumer.accept(column);
        }
    }


}
