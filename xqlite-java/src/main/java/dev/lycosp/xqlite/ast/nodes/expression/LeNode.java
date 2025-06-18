package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

import java.util.Objects;

public final class LeNode extends ComparisonExpression {
    public static LeNode le(ColumnNode column, Object value) {
        return new LeNode(column, value);
    }

    public static LeNode le(String column, Object value) {
        return new LeNode(ColumnNode.col(column), value);
    }

    private LeNode(ColumnNode column, Object value) {
        super(column, value);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitLe(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LeNode leNode = (LeNode) obj;
        return getColumn().equals(leNode.getColumn()) && getValue().equals(leNode.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getValue());
    }

    @Override
    public String toString() {
        return "LeNode{" +
                "column=" + getColumn() +
                ", value=" + getValue() +
                '}';
    }
}