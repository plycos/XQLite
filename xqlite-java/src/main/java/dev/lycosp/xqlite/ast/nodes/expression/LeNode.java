package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

public class LeNode extends ComparisonExpression {
    public static LeNode le(ColumnNode column, Object value) {
        return new LeNode(column, value);
    }

    public static LeNode le(String column, Object value) {
        return new LeNode(ColumnNode.col(column), value);
    }

    private LeNode(ColumnNode column, Object value) {
        super(column, value);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitLe(this);
    }
}