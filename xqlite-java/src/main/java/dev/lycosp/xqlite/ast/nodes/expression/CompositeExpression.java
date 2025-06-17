package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.utils.ListUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class CompositeExpression implements Expression {
    private final List<Expression> expressions;

    protected CompositeExpression(List<Expression> expressions) {
        this.expressions = Objects.requireNonNull(expressions, "Expressions cannot be null");
    }

    protected static List<Expression> toExpressionList(Expression... expressions) {
        return ListUtils.immutableCopy(Arrays.asList(expressions));
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
}
