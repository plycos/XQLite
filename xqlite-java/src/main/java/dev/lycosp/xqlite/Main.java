package dev.lycosp.xqlite;

import dev.lycosp.xqlite.ast.SqlRenderer;
import dev.lycosp.xqlite.ast.nodes.select.SelectNode;

import static dev.lycosp.xqlite.ast.Nodes.*;

public class Main {
    public static void main(String[] args) {
        SelectNode selectNode =
                select(
                        cols("col1", "col2", "col3"),
                        from("table")
                );
        System.out.println(SqlRenderer.render(selectNode));
    }
}
