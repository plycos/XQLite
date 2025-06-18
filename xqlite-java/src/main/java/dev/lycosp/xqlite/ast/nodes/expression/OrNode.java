package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class OrNode extends CompositeExpression {
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
        return visitor.visitOr(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrNode orNode = (OrNode) obj;
        return getExpressions().equals(orNode.getExpressions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExpressions());
    }

    @Override
    public String toString() {
        return "OrNode{" +
                "expressions=" + getExpressions() +
                '}';
    }
}
