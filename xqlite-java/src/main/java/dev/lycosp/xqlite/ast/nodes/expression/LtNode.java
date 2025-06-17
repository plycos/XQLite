package dev.lycosp.xqlite.ast.nodes.expression;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

public class LtNode extends ComparisonExpression {
    public static LtNode lt(ColumnNode column, Object value) {
        return new LtNode(column, value);
    }

    public static LtNode lt(String column, Object value) {
        return new LtNode(ColumnNode.col(column), value);
    }

    private LtNode(ColumnNode column, Object value) {
        super(column, value);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitLt(this);
    }
}