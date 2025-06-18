package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.utils.ListUtils;

import java.util.List;
import java.util.Objects;

abstract class CompositeExpression implements Expression {
    private final List<Expression> expressions;

    CompositeExpression(List<Expression> expressions) {
        this.expressions = Objects.requireNonNull(ListUtils.immutableCopy(expressions), "Expressions cannot be null");
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
