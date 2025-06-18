package dev.lycosp.xqlite.ast.nodes.select;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;
import dev.lycosp.xqlite.ast.nodes.ColumnsNode;
import dev.lycosp.xqlite.ast.nodes.TableNode;
import dev.lycosp.xqlite.ast.nodes.expression.Expression;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for constructing {@link SelectNode} instances from AST nodes.
 * <p>
 * The {@code SelectBuilder} provides a static method to build a {@link SelectNode}
 * by passing the required column and table nodes as arguments.
 * </p>
 */
public final class SelectBuilder {
    private SelectBuilder() {
    }

    /**
     * Constructs a {@link SelectNode} from the given arguments.
     * <p>
     * Example usage:
     * </p>
     * <pre>{@code
     * SelectNode selectNode =
     *     select(
     *         cols("col1", "col2", "col3"),
     *         from("table")
     *     );
     * }</pre>
     * <p>
     * Expects one {@link ColumnsNode} (for the columns) and one {@link TableNode} (for the source table).
     * Any unsupported or null arguments will result in an {@link IllegalArgumentException}
     * with a detailed error message listing the problematic argument indices and types.
     * </p>
     *
     * @param args the AST nodes to build the select statement from;
     *             must include exactly one {@link ColumnsNode} (for columns)
     *             and one {@link TableNode} (for the source table), in any order
     * @return a new {@link SelectNode} instance
     * @throws IllegalArgumentException if unsupported node types are provided,
     *                                  or if columns or table are missing
     */
    public static SelectNode select(SqlNode... args) {
        Map<Integer, SqlNode> unsupportedNodes = new LinkedHashMap<>();
        List<ColumnNode> columns = null;
        TableNode from = null;
        Expression where = null;

        for (int i = 0; i < args.length; i++) {
            SqlNode node = args[i];
            if (node instanceof TableNode) {
                from = (TableNode) node;
            } else if (node instanceof ColumnsNode) {
                columns = ((ColumnsNode) node).getColumns();
            } else if (node instanceof Expression) {
                where = (Expression) node;
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

        return SelectNode.create(columns, from, where);
    }
}
