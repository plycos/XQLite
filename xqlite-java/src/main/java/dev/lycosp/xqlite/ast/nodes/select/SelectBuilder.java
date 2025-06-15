package dev.lycosp.xqlite.ast.nodes.select;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;
import dev.lycosp.xqlite.ast.nodes.ColumnsNode;
import dev.lycosp.xqlite.ast.nodes.TableNode;

import java.util.List;

public final class SelectBuilder {
    private SelectBuilder() {
    }

    public static SelectNode select(SqlNode... args) {
        List<ColumnNode> columns = null;
        TableNode from = null;

        for (SqlNode node : args) {
            if (node instanceof TableNode) {
                from = (TableNode) node;
            } else if (node instanceof ColumnsNode) {
                columns = ((ColumnsNode) node).getColumns();
            } else {
                throw new IllegalArgumentException("Unsupported node type: " + node.getClass());
            }
        }

        if (columns == null || from == null) {
            throw new IllegalArgumentException("Both columns and from must be provided");
        }

        return SelectNode.create(columns, from);
    }
}
