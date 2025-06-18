package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

import java.util.Objects;

public final class LtNode extends ComparisonExpression {
    public static LtNode lt(ColumnNode column, Object value) {
        return new LtNode(column, value);
    }

    public static LtNode lt(String column, Object value) {
        return new LtNode(ColumnNode.col(column), value);
    }

    private LtNode(ColumnNode column, Object value) {
        super(column, value);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitLt(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LtNode ltNode = (LtNode) obj;
        return getColumn().equals(ltNode.getColumn()) && getValue().equals(ltNode.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getValue());
    }

    @Override
    public String toString() {
        return "LtNode{" +
                "column=" + getColumn() +
                ", value=" + getValue() +
                '}';
    }
}