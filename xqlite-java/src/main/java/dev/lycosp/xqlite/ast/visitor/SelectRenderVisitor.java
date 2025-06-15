package dev.lycosp.xqlite.ast.visitor;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.ColumnNode;
import dev.lycosp.xqlite.runtime.QuerySpec;

public class SelectRenderVisitor implements SqlVisitor<QuerySpec> {
    @Override
    public QuerySpec visit(SqlNode node) {
        return node.accept(this);
    }

    @Override
    public QuerySpec visitColumn(ColumnNode node) {
        String sql = node.getTableAlias() != null
                ? node.getTableAlias() + "." + node.getName()
                : node.getName();
        return QuerySpec.of(sql);
    }
}
