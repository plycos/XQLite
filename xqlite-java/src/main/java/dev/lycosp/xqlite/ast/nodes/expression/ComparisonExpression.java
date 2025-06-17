package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.nodes.ColumnNode;

public abstract class ComparisonExpression implements Expression {
    private final ColumnNode column;
    private final Object value;

    protected ComparisonExpression(ColumnNode column, Object value) {
        this.column = column;
        this.value = value;
    }

    public ColumnNode getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }
}
