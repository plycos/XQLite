package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

import java.util.Objects;

public class GtNode extends ComparisonExpression {
    public static GtNode gt(ColumnNode column, Object value) {
        return new GtNode(column, value);
    }

    public static GtNode gt(String column, Object value) {
        return new GtNode(ColumnNode.col(column), value);
    }

    private GtNode(ColumnNode column, Object value) {
        super(column, value);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitGt(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GtNode gtNode = (GtNode) obj;
        return getColumn().equals(gtNode.getColumn()) && getValue().equals(gtNode.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getValue());
    }

    @Override
    public String toString() {
        return "GtNode{" +
                "column=" + getColumn() +
                ", value=" + getValue() +
                '}';
    }
}