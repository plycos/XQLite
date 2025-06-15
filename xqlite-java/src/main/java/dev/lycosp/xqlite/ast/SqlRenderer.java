package dev.lycosp.xqlite.ast;

import dev.lycosp.xqlite.ast.visitor.SelectRenderVisitor;
import dev.lycosp.xqlite.runtime.QuerySpec;

public final class SqlRenderer {
    private SqlRenderer() {}

    public static QuerySpec render(SqlNode node) {
        return new SelectRenderVisitor().visit(node);
    }
}
