package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.nodes.ColumnNode;

import java.util.Objects;

abstract class ComparisonExpression implements Expression {
    private final ColumnNode column;
    private final Object value;

    ComparisonExpression(ColumnNode column, Object value) {
        this.column = column;
        this.value = Objects.requireNonNull(value, "Comparison value must not be null");
    }

    public ColumnNode getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }
}
