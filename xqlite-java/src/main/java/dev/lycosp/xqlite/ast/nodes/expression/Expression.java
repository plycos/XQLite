package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.SqlVisitor;

public interface Expression extends SqlNode {
    static Expression where(Expression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("Where must not be null");
        }
        return expression;
    }

    static Expression emptyExpression() {
        return new Expression() {
            @Override
            public <R> R accept(SqlVisitor<R> visitor) {
                return null;
            }

            @Override
            public String toString() {
                return "";
            }
        };
    }
}
