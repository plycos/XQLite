package dev.lycosp.xqlite.ast.nodes.orderby;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;

import java.util.Objects;

public abstract class OrderByNode implements SqlNode {
    private final ColumnNode column;
    private final SortDirection direction;

    OrderByNode(ColumnNode column, SortDirection direction) {
        this.column = column;
        this.direction = Objects.requireNonNull(direction, "Direction cannot be null");
    }

    public ColumnNode getColumn() {
        return column;
    }

    public SortDirection getDirection() {
        return direction;
    }
}
