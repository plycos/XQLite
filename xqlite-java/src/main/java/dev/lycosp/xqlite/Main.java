package dev.lycosp.xqlite;

import dev.lycosp.xqlite.ast.SqlRenderer;
import dev.lycosp.xqlite.ast.nodes.TableNode;
import dev.lycosp.xqlite.ast.nodes.select.SelectNode;

import static dev.lycosp.xqlite.ast.nodes.ColumnsNode.cols;
import static dev.lycosp.xqlite.ast.nodes.TableNode.from;
import static dev.lycosp.xqlite.ast.nodes.TableNode.table;
import static dev.lycosp.xqlite.ast.nodes.select.SelectBuilder.select;

public class Main {
    public static void main(String[] args) {
        TableNode t = table("table", "t");
        SelectNode selectNode =
                select(
                        from(t),
                        cols(t.col("test"))
                );
        System.out.println(SqlRenderer.render(selectNode));

    }
}
