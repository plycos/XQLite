package dev.lycosp.xqlite.ast.nodes.orderby;

import dev.lycosp.xqlite.ast.SqlVisitor;

import java.util.Objects;

public interface OrderBy {
    static OrderByNode emptyOrderBy() {
        return new OrderByNode(null, SortDirection.ASC) {
            @Override
            public <R> R accept(SqlVisitor<R> visitor) {
                return null;
            }

            @Override
            public int hashCode() {
                return Objects.hash(getColumn(), getDirection());
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == this) return true;
                if (!(obj instanceof OrderByNode)) return false;
                OrderByNode orderBy = (OrderByNode) obj;
                return Objects.equals(getColumn(), orderBy.getColumn()) &&
                        Objects.equals(getDirection(), orderBy.getDirection());
            }

            @Override
            public String toString() {
                return "";
            }
        };
    }
}
