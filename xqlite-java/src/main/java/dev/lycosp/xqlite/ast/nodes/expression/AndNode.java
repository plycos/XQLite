package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;

import java.util.Arrays;
import java.util.List;

public class AndNode extends CompositeExpression {
    public static AndNode and(Expression... children) {
        return new AndNode(Arrays.asList(children));
    }

    public static AndNode and(List<Expression> expressions) {
        return new AndNode(expressions);
    }

    private AndNode(List<Expression> expressions) {
        super(expressions);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitAnd(this);
    }
}
