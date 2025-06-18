package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class AndNode extends CompositeExpression {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AndNode andNode = (AndNode) obj;
        return getExpressions().equals(andNode.getExpressions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExpressions());
    }

    @Override
    public String toString() {
        return "AndNode{" +
                "expressions=" + getExpressions() +
                '}';
    }
}
