package dev.lycosp.xqlite.query.builder;

import dev.lycosp.xqlite.query.QuerySpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SelectBuilder implements QueryBuilder {
    private List<Column> columns;
    private String from;

    public SelectBuilder columns(String... columns) {
        List<Column> cols = new ArrayList<>(columns.length);
        for (String column : columns) {
            cols.add(Column.col(column));
        }
        this.columns = Collections.unmodifiableList(cols);
        return this;
    }

    public SelectBuilder columns(List<Column> columns) {
        this.columns = Collections.unmodifiableList(
                new ArrayList<>(columns)
        );
        return this;
    }

    public SelectBuilder columns(Column... columns) {
        this.columns = Collections.unmodifiableList(
                new ArrayList<>(Arrays.asList(columns))
        );
        return this;
    }

    @Override
    public QuerySpec build() {
        return null;
    }
}
