package dev.lycosp.xqlite.ast.nodes.select;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;
import dev.lycosp.xqlite.ast.nodes.TableNode;

import java.util.List;
import java.util.Objects;

public final class SelectNode implements SqlNode {
    private final List<ColumnNode> columns;
    private final TableNode from;

    static SelectNode create(List<ColumnNode> columns, TableNode from) {
        return new SelectNode(columns, from);
    }

    private SelectNode(List<ColumnNode> columns, TableNode from) {
        this.columns = Objects.requireNonNull(columns, "Columns must not be null");
        this.from = Objects.requireNonNull(from, "From must not be null");
    }

    public List<ColumnNode> getColumns() {
        return columns;
    }

    public TableNode getFrom() {
        return from;
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitSelect(this);
    }
}
