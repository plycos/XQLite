package dev.lycosp.jqlite.query.builder;

import java.util.Objects;

public class Column {
    private final String tableAlias;
    private final String columnName;

    public static Column col(String columnName) {
        return new Column(null, columnName);
    }

    public static Column col(String tableAlias, String columnName) {
        return new Column(tableAlias, columnName);
    }

    private Column(String tableAlias, String columnName) {
        this.tableAlias = tableAlias;
        this.columnName = columnName;
    }

    public String toSql() {
        return tableAlias != null ? tableAlias + "." + columnName : columnName;
    }

    @Override
    public String toString() {
        return toSql();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return Objects.equals(tableAlias, column.tableAlias) &&
                Objects.equals(columnName, column.columnName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableAlias, columnName);
    }
}
