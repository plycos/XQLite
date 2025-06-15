package dev.lycosp.xqlite.ast;

import dev.lycosp.xqlite.ast.nodes.ColumnNode;

public interface SqlVisitor<R> {
    R visit(SqlNode node);
    R visitColumn(ColumnNode node);
}
