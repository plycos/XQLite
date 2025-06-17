package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

public class GeNode extends ComparisonExpression {
    public static GeNode ge(ColumnNode column, Object value) {
        return new GeNode(column, value);
    }

    public static GeNode ge(String column, Object value) {
        return new GeNode(ColumnNode.col(column), value);
    }

    private GeNode(ColumnNode column, Object value) {
        super(column, value);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitGe(this);
    }
}