package dev.lycosp.xqlite.ast.visitor;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;
import dev.lycosp.xqlite.ast.nodes.TableNode;
import dev.lycosp.xqlite.ast.nodes.expression.*;
import dev.lycosp.xqlite.ast.nodes.orderby.OrderByNode;
import dev.lycosp.xqlite.ast.nodes.orderby.OrderByNodes;
import dev.lycosp.xqlite.ast.nodes.select.SelectNode;
import dev.lycosp.xqlite.runtime.QuerySpec;

import java.util.ArrayList;
import java.util.List;

public final class SelectRenderVisitor implements SqlVisitor<QuerySpec> {
    @Override
    public QuerySpec visit(SqlNode node) {
        return node.accept(this);
    }

    @Override
    public QuerySpec visitSelect(SelectNode node) {
        String columnsSql = VisitorUtils.generateColumnsSql(node.getColumns(), this);
        String fromSql = visitTable(node.getFrom()).getSql();

        StringBuilder sqlBuilder = new StringBuilder("SELECT ")
                .append(columnsSql)
                .append(" FROM ")
                .append(fromSql);

        List<Object> params = new ArrayList<>();
        QuerySpec whereSpec = visit(node.getWhere());
        if (whereSpec != null) {
            sqlBuilder.append(" WHERE ").append(whereSpec.getSql());
            params.addAll(whereSpec.getParams());
        }

        if (!node.getOrderBy().isEmpty()) {
            QuerySpec orderBySpec = visit(node.getOrderBy());
            sqlBuilder.append(" ORDER BY ").append(orderBySpec.getSql());
        }

        return QuerySpec.of(sqlBuilder.toString(), params);
    }

    @Override
    public QuerySpec visitColumn(ColumnNode node) {
        String sql = node.getTableAlias() != null
                ? node.getTableAlias() + "." + node.getName()
                : node.getName();
        return QuerySpec.of(sql);
    }

    @Override
    public QuerySpec visitTable(TableNode node) {
        String table = node.getAlias() != null
                ? node.getName() + " " + node.getAlias()
                : node.getName();
        return QuerySpec.of(table);
    }

    @Override
    public QuerySpec visitAnd(AndNode node) {
        return VisitorUtils.visitCompositeExpressionNode(node.getExpressions(), "AND", this);
    }

    @Override
    public QuerySpec visitOr(OrNode node) {
        return VisitorUtils.visitCompositeExpressionNode(node.getExpressions(), "OR", this);
    }

    @Override
    public QuerySpec visitEq(EqNode node) {
        String column = visitColumn(node.getColumn()).getSql();
        String sql = column + " = ?";
        return QuerySpec.of(sql, node.getValue());
    }

    @Override
    public QuerySpec visitLt(LtNode node) {
        String column = visitColumn(node.getColumn()).getSql();
        String sql = column + " < ?";
        return QuerySpec.of(sql, node.getValue());
    }

    @Override
    public QuerySpec visitGt(GtNode node) {
        String column = visitColumn(node.getColumn()).getSql();
        String sql = column + " > ?";
        return QuerySpec.of(sql, node.getValue());
    }

    @Override
    public QuerySpec visitLe(LeNode node) {
        String column = visitColumn(node.getColumn()).getSql();
        String sql = column + " <= ?";
        return QuerySpec.of(sql, node.getValue());
    }

    @Override
    public QuerySpec visitGe(GeNode node) {
        String column = visitColumn(node.getColumn()).getSql();
        String sql = column + " >= ?";
        return QuerySpec.of(sql, node.getValue());
    }

    @Override
    public QuerySpec visitNe(NeNode node) {
        String column = visitColumn(node.getColumn()).getSql();
        String sql = column + " <> ?";
        return QuerySpec.of(sql, node.getValue());
    }

    @Override
    public QuerySpec visitOrderBys(OrderByNodes node) {
        List<OrderByNode> orderByList = node.getNodes();
        StringBuilder orderBySql = new StringBuilder();
        for (int i = 0; i < orderByList.size(); i++) {
            OrderByNode orderByNode = orderByList.get(i);
            QuerySpec orderBy = visit(orderByNode);
            orderBySql.append(orderBy.getSql());
            if (i < orderByList.size() - 1) {
                orderBySql.append(", ");
            }
        }
        return QuerySpec.of(orderBySql.toString());
    }

    @Override
    public QuerySpec visitOrderBy(OrderByNode node) {
        String column = visitColumn(node.getColumn()).getSql();
        return QuerySpec.of(column + " " + node.getDirection());
    }
}
