package dev.lycosp.xqlite.api;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;
import dev.lycosp.xqlite.ast.nodes.ColumnsNode;
import dev.lycosp.xqlite.ast.nodes.TableNode;
import dev.lycosp.xqlite.ast.nodes.expression.*;
import dev.lycosp.xqlite.ast.nodes.orderby.AscNode;
import dev.lycosp.xqlite.ast.nodes.orderby.DescNode;
import dev.lycosp.xqlite.ast.nodes.orderby.OrderByNode;
import dev.lycosp.xqlite.ast.nodes.orderby.OrderByNodes;
import dev.lycosp.xqlite.ast.nodes.select.SelectBuilder;
import dev.lycosp.xqlite.ast.nodes.select.SelectNode;

import java.util.List;

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


    // --- Expression: Where ---

    /**
     * Delegates to {@link Expression#where(Expression)}.
     *
     * @see Expression#where(Expression)
     */
    public static Expression where(Expression node) {
        return Expression.where(node);
    }


    // --- Expression: AndNode ---

    /**
     * Delegates to {@link AndNode#and(Expression...)}.
     *
     * @see AndNode#and(Expression...)
     */
    public static AndNode and(Expression... nodes) {
        return AndNode.and(nodes);
    }

    /**
     * Delegates to {@link AndNode#and(List)}.
     *
     * @see AndNode#and(List)
     */
    public static AndNode and(List<Expression> nodes) {
        return AndNode.and(nodes);
    }

    // --- Expression: OrNode ---

    /**
     * Delegates to {@link OrNode#or(Expression...)}.
     *
     * @see OrNode#or(Expression...)
     */
    public static OrNode or(Expression... nodes) {
        return OrNode.or(nodes);
    }

    /**
     * Delegates to {@link OrNode#or(List)}.
     *
     * @see OrNode#or(List)
     */
    public static OrNode or(List<Expression> nodes) {
        return OrNode.or(nodes);
    }

    // --- Expression: EqNode ---

    /**
     * Delegates to {@link EqNode#eq(ColumnNode, Object)}.
     *
     * @see EqNode#eq(ColumnNode, Object)
     */
    public static EqNode eq(ColumnNode column, Object value) {
        return EqNode.eq(column, value);
    }

    /**
     * Delegates to {@link EqNode#eq(String, Object)}.
     *
     * @see EqNode#eq(String, Object)
     */
    public static EqNode eq(String column, Object value) {
        return EqNode.eq(column, value);
    }

    // --- Expression: LtNode ---

    /**
     * Delegates to {@link LtNode#lt(ColumnNode, Object)}.
     *
     * @see LtNode#lt(ColumnNode, Object)
     */
    public static LtNode lt(ColumnNode column, Object value) {
        return LtNode.lt(column, value);
    }

    /**
     * Delegates to {@link LtNode#lt(String, Object)}.
     *
     * @see LtNode#lt(String, Object)
     */
    public static LtNode lt(String column, Object value) {
        return LtNode.lt(column, value);
    }

    // --- Expression: GtNode ---

    /**
     * Delegates to {@link GtNode#gt(ColumnNode, Object)}.
     *
     * @see GtNode#gt(ColumnNode, Object)
     */
    public static GtNode gt(ColumnNode column, Object value) {
        return GtNode.gt(column, value);
    }

    /**
     * Delegates to {@link GtNode#gt(String, Object)}.
     *
     * @see GtNode#gt(String, Object)
     */
    public static GtNode gt(String column, Object value) {
        return GtNode.gt(column, value);
    }

    // --- Expression: LeNode ---

    /**
     * Delegates to {@link LeNode#le(ColumnNode, Object)}.
     *
     * @see LeNode#le(ColumnNode, Object)
     */
    public static LeNode le(ColumnNode column, Object value) {
        return LeNode.le(column, value);
    }

    /**
     * Delegates to {@link LeNode#le(String, Object)}.
     *
     * @see LeNode#le(String, Object)
     */
    public static LeNode le(String column, Object value) {
        return LeNode.le(column, value);
    }

    // --- Expression: GeNode ---

    /**
     * Delegates to {@link GeNode#ge(ColumnNode, Object)}.
     *
     * @see GeNode#ge(ColumnNode, Object)
     */
    public static GeNode ge(ColumnNode column, Object value) {
        return GeNode.ge(column, value);
    }

    /**
     * Delegates to {@link GeNode#ge(String, Object)}.
     *
     * @see GeNode#ge(String, Object)
     */
    public static GeNode ge(String column, Object value) {
        return GeNode.ge(column, value);
    }

    // --- Expression: NeNode ---

    /**
     * Delegates to {@link NeNode#ne(ColumnNode, Object)}.
     *
     * @see NeNode#ne(ColumnNode, Object)
     */
    public static NeNode ne(ColumnNode column, Object value) {
        return NeNode.ne(column, value);
    }

    /**
     * Delegates to {@link NeNode#ne(String, Object)}.
     *
     * @see NeNode#ne(String, Object)
     */
    public static NeNode ne(String column, Object value) {
        return NeNode.ne(column, value);
    }


    // --- OrderByNodes ---

    /**
     * Delegates to {@link OrderByNodes#orderBy(OrderByNode...)}.
     *
     * @see OrderByNodes#orderBy(OrderByNode...)
     */
    public static OrderByNodes orderBy(OrderByNode... nodes) {
        return OrderByNodes.orderBy(nodes);
    }

    /**
     * Delegates to {@link OrderByNodes#orderBy(List)}.
     *
     * @see OrderByNodes#orderBy(List)
     */
    public static OrderByNodes orderBy(List<OrderByNode> nodes) {
        return OrderByNodes.orderBy(nodes);
    }

    // --- OrderByNode ---

    /**
     * Delegates to {@link AscNode#asc(String)}.
     *
     * @see AscNode#asc(String)
     */
    public static AscNode asc(String column) {
        return AscNode.asc(column);
    }

    /**
     * Delegates to {@link AscNode#asc(ColumnNode)}.
     *
     * @see AscNode#asc(ColumnNode)
     */
    public static AscNode asc(ColumnNode column) {
        return AscNode.asc(column);
    }

    /**
     * Delegates to {@link DescNode#desc(String)}.
     *
     * @see DescNode#desc(String)
     */
    public static DescNode desc(String column) {
        return DescNode.desc(column);
    }

    /**
     * Delegates to {@link DescNode#desc(ColumnNode)}.
     *
     * @see DescNode#desc(ColumnNode)
     */
    public static DescNode desc(ColumnNode column) {
        return DescNode.desc(column);
    }
}
