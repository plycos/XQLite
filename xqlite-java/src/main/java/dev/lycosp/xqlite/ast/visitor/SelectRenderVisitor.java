package dev.lycosp.xqlite.ast.visitor;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;
import dev.lycosp.xqlite.ast.nodes.TableNode;
import dev.lycosp.xqlite.ast.nodes.expression.*;
import dev.lycosp.xqlite.ast.nodes.select.SelectNode;
import dev.lycosp.xqlite.runtime.QuerySpec;

import java.util.List;

public final class SelectRenderVisitor implements SqlVisitor<QuerySpec> {
    @Override
    public QuerySpec visit(SqlNode node) {
        return node.accept(this);
    }

    @Override
    public QuerySpec visitSelect(SelectNode node) {
        List<ColumnNode> columns = node.getColumns();
        StringBuilder columnsSql = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            columnsSql.append(visitColumn(columns.get(i)).getSql());
            if (i < columns.size() - 1) {
                columnsSql.append(", ");
            }
        }

        String fromSql = visitTable(node.getFrom()).getSql();

        String sql = "SELECT " + columnsSql + " FROM " + fromSql;
        return QuerySpec.of(sql);
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
        return null;
    }

    @Override
    public QuerySpec visitOr(OrNode node) {
        return null;
    }

    @Override
    public QuerySpec visitEq(EqNode node) {
        return null;
    }

    @Override
    public QuerySpec visitLt(LtNode node) {
        return null;
    }

    @Override
    public QuerySpec visitGt(GtNode node) {
        return null;
    }

    @Override
    public QuerySpec visitLe(LeNode node) {
        return null;
    }

    @Override
    public QuerySpec visitGe(GeNode node) {
        return null;
    }

    @Override
    public QuerySpec visitNe(NeNode node) {
        return null;
    }
}
