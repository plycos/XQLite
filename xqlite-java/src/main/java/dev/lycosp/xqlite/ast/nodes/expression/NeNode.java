package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

public class NeNode extends ComparisonExpression {
    public static NeNode ne(ColumnNode column, Object value) {
        return new NeNode(column, value);
    }

    public static NeNode ne(String column, Object value) {
        return new NeNode(ColumnNode.col(column), value);
    }

    private NeNode(ColumnNode column, Object value) {
        super(column, value);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitNe(this);
    }
}