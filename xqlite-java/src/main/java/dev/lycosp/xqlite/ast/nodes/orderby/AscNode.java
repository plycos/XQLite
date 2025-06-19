package dev.lycosp.xqlite.ast.nodes.orderby;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

import java.util.Objects;

public final class AscNode extends OrderByNode {
    public static AscNode asc(String column) {
        return new AscNode(ColumnNode.col(column), SortDirection.ASC);
    }

    public static AscNode asc(ColumnNode column) {
        return new AscNode(column, SortDirection.ASC);
    }

    private AscNode(ColumnNode column, SortDirection direction) {
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
        AscNode ascNode = (AscNode) obj;
        return getColumn().equals(ascNode.getColumn()) && getDirection().equals(ascNode.getDirection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getDirection());
    }

    @Override
    public String toString() {
        return "AscNode{" +
                "column=" + getColumn() +
                ", direction='" + getDirection() + '\'' +
                '}';
    }
}
