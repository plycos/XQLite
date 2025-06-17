package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

public class GtNode extends ComparisonExpression {
    public static GtNode gt(ColumnNode column, Object value) {
        return new GtNode(column, value);
    }

    public static GtNode gt(String column, Object value) {
        return new GtNode(ColumnNode.col(column), value);
    }

    private GtNode(ColumnNode column, Object value) {
        super(column, value);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitGt(this);
    }
}