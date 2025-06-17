package dev.lycosp.xqlite.ast;

import dev.lycosp.xqlite.ast.nodes.ColumnNode;
import dev.lycosp.xqlite.ast.nodes.TableNode;
import dev.lycosp.xqlite.ast.nodes.expression.*;
import dev.lycosp.xqlite.ast.nodes.select.SelectNode;

public interface SqlVisitor<R> {
    R visit(SqlNode node);

    R visitSelect(SelectNode node);

    R visitColumn(ColumnNode node);

    R visitTable(TableNode node);

    R visitAnd(AndNode node);

    R visitOr(OrNode node);

    R visitEq(EqNode node);

    R visitLt(LtNode node);

    R visitGt(GtNode node);

    R visitLe(LeNode node);

    R visitGe(GeNode node);

    R visitNe(NeNode node);
}
