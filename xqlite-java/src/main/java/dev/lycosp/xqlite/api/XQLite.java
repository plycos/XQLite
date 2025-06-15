package dev.lycosp.xqlite.api;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;
import dev.lycosp.xqlite.ast.nodes.ColumnsNode;
import dev.lycosp.xqlite.ast.nodes.TableNode;
import dev.lycosp.xqlite.ast.nodes.select.SelectBuilder;
import dev.lycosp.xqlite.ast.nodes.select.SelectNode;

/**
 * Utility class providing static factory and delegation methods for constructing
 * SQL AST nodes such as {@link ColumnNode}, {@link ColumnsNode}, {@link TableNode},
 * and {@link SelectNode}.
 *
 * <p>
 * Repository: <a href="https://github.com/plycos/xqlite">https://github.com/plycos/xqlite</a>
 * </p>
 *
 * <p>
 * Designed for use with static imports to enable concise and expressive query construction.
 * </p>
 *
 * <pre>
 * {@code
 * import static dev.lycosp.xqlite.api.XQLite.*;
 *
 * SelectNode selectNode = select(
 *     cols("col1", "col2", "col3"),
 *     from("table")
 * );
 * }
 * </pre>
 *
 * @author <a href="https://github.com/plycos">Peter Lycos (@plycos)</a>
 */
public final class XQLite {
    private XQLite() {
    }

    // --- SelectNode ---

    /**
     * Delegates to {@link SelectBuilder#select(SqlNode...)}.
     *
     * @see SelectBuilder#select(SqlNode...)
     */
    public static SelectQuery select(SqlNode... args) {
        return SelectQuery.select(args);
    }


    // --- ColumnNode ---

    /**
     * Delegates to {@link ColumnNode#col(String)}.
     *
     * @see ColumnNode#col(String)
     */
    public static ColumnNode col(String name) {
        return ColumnNode.col(name);
    }

    /**
     * Delegates to {@link ColumnNode#col(String, String)}.
     *
     * @see ColumnNode#col(String, String)
     */
    public static ColumnNode col(String tableAlias, String name) {
        return ColumnNode.col(tableAlias, name);
    }


    // --- ColumnsNode ---

    /**
     * Delegates to {@link ColumnsNode#cols(String...)}.
     *
     * @see ColumnsNode#cols(String...)
     */
    public static ColumnsNode cols(String... columns) {
        return ColumnsNode.cols(columns);
    }

    /**
     * Delegates to {@link ColumnsNode#cols(ColumnNode...)}.
     *
     * @see ColumnsNode#cols(ColumnNode...)
     */
    public static ColumnsNode cols(ColumnNode... columns) {
        return ColumnsNode.cols(columns);
    }

    /**
     * Delegates to {@link ColumnsNode#cols(java.util.List)}.
     *
     * @see ColumnsNode#cols(java.util.List)
     */
    public static ColumnsNode cols(java.util.List<ColumnNode> columns) {
        return ColumnsNode.cols(columns);
    }

    /**
     * Delegates to {@link ColumnsNode#colStrings(java.util.List)}.
     *
     * @see ColumnsNode#colStrings(java.util.List)
     */
    public static ColumnsNode colStrings(java.util.List<String> columns) {
        return ColumnsNode.colStrings(columns);
    }


    // --- TableNode ---

    /**
     * Delegates to {@link TableNode#table(String)}.
     *
     * @see TableNode#table(String)
     */
    public static TableNode table(String name) {
        return TableNode.table(name);
    }

    /**
     * Delegates to {@link TableNode#table(String, String)}.
     *
     * @see TableNode#table(String, String)
     */
    public static TableNode table(String name, String alias) {
        return TableNode.table(name, alias);
    }

    /**
     * Delegates to {@link TableNode#from(TableNode)}.
     *
     * @see TableNode#from(TableNode)
     */
    public static TableNode from(TableNode table) {
        return TableNode.from(table);
    }

    /**
     * Delegates to {@link TableNode#from(String)}.
     *
     * @see TableNode#from(String)
     */
    public static TableNode from(String name) {
        return TableNode.from(name);
    }

    /**
     * Delegates to {@link TableNode#from(String, String)}.
     *
     * @see TableNode#from(String, String)
     */
    public static TableNode from(String name, String alias) {
        return TableNode.from(name, alias);
    }
}
