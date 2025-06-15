package dev.lycosp.xqlite.ast.nodes.select;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;
import dev.lycosp.xqlite.ast.nodes.ColumnsNode;
import dev.lycosp.xqlite.ast.nodes.TableNode;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class SelectBuilder {
    private SelectBuilder() {
    }

    public static SelectNode select(SqlNode... args) {
        Map<Integer, SqlNode> unsupportedNodes = new LinkedHashMap<>();
        List<ColumnNode> columns = null;
        TableNode from = null;

        for (int i = 0; i < args.length; i++) {
            SqlNode node = args[i];
            if (node instanceof TableNode) {
                from = (TableNode) node;
            } else if (node instanceof ColumnsNode) {
                columns = ((ColumnsNode) node).getColumns();
            } else {
                unsupportedNodes.put(i, node);
            }
        }

        if (!unsupportedNodes.isEmpty()) {
            StringBuilder error = new StringBuilder("Unsupported node types in SelectBuilder:\n");
            for (Map.Entry<Integer, SqlNode> entry : unsupportedNodes.entrySet()) {
                error.append("  [").append(entry.getKey()).append("]  ");
                SqlNode n = entry.getValue();
                if (n == null) {
                    error.append("null\n");
                } else {
                    error.append(n.getClass().getSimpleName())
                            .append(": ")
                            .append(n)
                            .append("\n");
                }
            }
            throw new IllegalArgumentException(error.toString());
        }

        if (columns == null || from == null) {
            throw new IllegalArgumentException("Both columns and from must be provided");
        }

        return SelectNode.create(columns, from);
    }
}
