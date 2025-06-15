package dev.lycosp.xqlite.ast.nodes;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.utils.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ColumnsNode implements SqlNode {
    private final List<ColumnNode> columns;

    public static ColumnsNode cols(String... columns) {
        List<ColumnNode> columnNodes = new ArrayList<>();
        for (String column : columns) {
            columnNodes.add(ColumnNode.col(column));
        }
        return new ColumnsNode(columnNodes);
    }

    public static ColumnsNode colStrings(List<String> columns) {
        List<ColumnNode> columnNodes = new ArrayList<>();
        for (String column : columns) {
            columnNodes.add(ColumnNode.col(column));
        }
        return new ColumnsNode(columnNodes);
    }

    public static ColumnsNode cols(ColumnNode... columns) {
        List<ColumnNode> columnNodes = new ArrayList<>();
        Collections.addAll(columnNodes, columns);
        return new ColumnsNode(columnNodes);
    }

    public static ColumnsNode cols(List<ColumnNode> columns) {
        return new ColumnsNode(columns);
    }

    private ColumnsNode(List<ColumnNode> columns) {
        this.columns = ListUtils.immutableCopy(Objects.requireNonNull(columns, "Columns must not be null"));
    }

    public List<ColumnNode> getColumns() {
        return columns;
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColumnsNode that = (ColumnsNode) o;
        return Objects.equals(columns, that.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(columns);
    }

    @Override
    public String toString() {
        return "ColumnsNode{" +
                "columns=" + columns +
                '}';
    }
}
