package dev.lycosp.xqlite.ast;

import dev.lycosp.xqlite.ast.nodes.ColumnNode;
import dev.lycosp.xqlite.ast.nodes.TableNode;
import dev.lycosp.xqlite.ast.nodes.select.SelectNode;

public interface SqlVisitor<R> {
    R visit(SqlNode node);
    R visitSelect(SelectNode node);
    R visitColumn(ColumnNode node);
    R visitTable(TableNode node);
}
