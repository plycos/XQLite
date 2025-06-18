package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

import java.util.Objects;

public final class NeNode extends ComparisonExpression {
    public static NeNode ne(ColumnNode column, Object value) {
        return new NeNode(column, value);
    }

    public static NeNode ne(String column, Object value) {
        return new NeNode(ColumnNode.col(column), value);
    }

    private NeNode(ColumnNode column, Object value) {
        super(column, value);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitNe(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NeNode neNode = (NeNode) obj;
        return getColumn().equals(neNode.getColumn()) && getValue().equals(neNode.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getValue());
    }

    @Override
    public String toString() {
        return "NeNode{" +
                "column=" + getColumn() +
                ", value=" + getValue() +
                '}';
    }
}