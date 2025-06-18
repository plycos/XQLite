package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

import java.util.Objects;

public class EqNode extends ComparisonExpression {
    public static EqNode eq(ColumnNode column, Object value) {
        return new EqNode(column, value);
    }

    public static EqNode eq(String column, Object value) {
        return new EqNode(ColumnNode.col(column), value);
    }

    private EqNode(ColumnNode column, Object value) {
        super(column, value);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitEq(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EqNode eqNode = (EqNode) obj;
        return getColumn().equals(eqNode.getColumn()) && getValue().equals(eqNode.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getValue());
    }

    @Override
    public String toString() {
        return "EqNode{" +
                "column=" + getColumn() +
                ", value=" + getValue() +
                '}';
    }
}