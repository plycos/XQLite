package dev.lycosp.xqlite.ast.nodes;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.utils.StringUtils;

import java.util.Objects;

public final class TableNode implements SqlNode {
    private final String name;
    private final String alias;

    public static TableNode table(String name) {
        return new TableNode(name, null);
    }

    public static TableNode table(String name, String alias) {
        return new TableNode(name, alias);
    }

    public static TableNode from(TableNode table) {
        return new TableNode(table.getName(), table.getAlias());
    }

    public static TableNode from(String name) {
        return new TableNode(name, null);
    }

    public static TableNode from(String name, String alias) {
        return new TableNode(name, alias);
    }

    private TableNode(String name, String alias) {
        this.name = StringUtils.requireNonBlank(name, "Table name cannot be blank");
        this.alias = alias;
    }

    public ColumnNode col(String name) {
        return alias == null
                ? ColumnNode.col(name)
                : ColumnNode.col(alias, name);
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitTable(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableNode tableNode = (TableNode) o;
        return Objects.equals(name, tableNode.name) && Objects.equals(alias, tableNode.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, alias);
    }

    @Override
    public String toString() {
        return "TableNode{" +
                "name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
