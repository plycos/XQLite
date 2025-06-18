package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;

import java.util.Arrays;
import java.util.List;

public class OrNode extends CompositeExpression {
    public static OrNode or(Expression... expressions) {
        return new OrNode(Arrays.asList(expressions));
    }

    public static OrNode or(List<Expression> expressions) {
        return new OrNode(expressions);
    }

    private OrNode(List<Expression> expressions) {
        super(expressions);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return null;
    }
}
