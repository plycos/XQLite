package dev.lycosp.xqlite.ast.nodes.select;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;
import dev.lycosp.xqlite.ast.nodes.TableNode;
import dev.lycosp.xqlite.ast.nodes.expression.Expression;

import java.util.List;
import java.util.Objects;

public final class SelectNode implements SqlNode {
    private final List<ColumnNode> columns;
    private final TableNode from;
    private final Expression where;

    static SelectNode create(List<ColumnNode> columns, TableNode from, Expression where) {
        return new SelectNode(columns, from, where);
    }

    private SelectNode(List<ColumnNode> columns, TableNode from, Expression where) {
        this.columns = Objects.requireNonNull(columns, "Columns must not be null");
        this.from = Objects.requireNonNull(from, "From must not be null");
        this.where = where;
    }

    public List<ColumnNode> getColumns() {
        return columns;
    }

    public TableNode getFrom() {
        return from;
    }

    public Expression getWhere() {
        return where;
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitSelect(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectNode that = (SelectNode) o;
        return Objects.equals(columns, that.columns) && Objects.equals(from, that.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columns, from);
    }

    @Override
    public String toString() {
        return "SelectNode{" +
                "columns=" + columns +
                ", from=" + from +
                '}';
    }
}
