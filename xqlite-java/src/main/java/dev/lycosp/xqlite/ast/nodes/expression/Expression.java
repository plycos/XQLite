package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlNode;

public interface Expression extends SqlNode {
    static Expression where(Expression expression) {
        if (expression == null) {
            throw new IllegalArgumentException("Where must not be null");
        }
        return expression;
    }
}
