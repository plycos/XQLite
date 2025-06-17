package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

public class EqNode extends ComparisonExpression {
    public static EqNode eq(ColumnNode column, Object value) {
        return new EqNode(column, value);
    }

    public static EqNode eq(String column, Object value) {
        return new EqNode(ColumnNode.col(column), value);
    }

    private EqNode(ColumnNode column, Object value) {
        super(column, value);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitEq(this);
    }
}
