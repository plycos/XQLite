package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

import java.util.Objects;

public final class GeNode extends ComparisonExpression {
    public static GeNode ge(ColumnNode column, Object value) {
        return new GeNode(column, value);
    }

    public static GeNode ge(String column, Object value) {
        return new GeNode(ColumnNode.col(column), value);
    }

    private GeNode(ColumnNode column, Object value) {
        super(column, value);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitGe(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GeNode geNode = (GeNode) obj;
        return getColumn().equals(geNode.getColumn()) && getValue().equals(geNode.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getValue());
    }

    @Override
    public String toString() {
        return "GeNode{" +
                "column=" + getColumn() +
                ", value=" + getValue() +
                '}';
    }
}