package dev.lycosp.xqlite.ast.nodes.orderby;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

import java.util.Objects;

public final class DescNode extends OrderByNode {
    public static DescNode desc(String column) {
        return new DescNode(ColumnNode.col(column), SortDirection.DESC);
    }

    public static DescNode desc(ColumnNode column) {
        return new DescNode(column, SortDirection.DESC);
    }

    private DescNode(ColumnNode column, SortDirection direction) {
        super(column, direction);
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitOrderBy(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DescNode descNode = (DescNode) obj;
        return getColumn().equals(descNode.getColumn()) && getDirection().equals(descNode.getDirection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getDirection());
    }

    @Override
    public String toString() {
        return "DescNode{" +
                "column=" + getColumn() +
                ", direction='" + getDirection() + '\'' +
                '}';
    }
}
