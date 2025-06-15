package dev.lycosp.xqlite.ast.nodes;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.SqlVisitor;

import java.util.Objects;

public class ColumnNode implements SqlNode {
    private final String tableAlias;
    private final String name;

    public static ColumnNode col(String name) {
        return new ColumnNode(null, name);
    }

    public static ColumnNode col(String tableAlias, String name) {
        return new ColumnNode(tableAlias, name);
    }

    private ColumnNode(String tableAlias, String name) {
        this.tableAlias = tableAlias;
        this.name = Objects.requireNonNull(name);
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public String getName() {
        return name;
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitColumn(this);
    }
}
