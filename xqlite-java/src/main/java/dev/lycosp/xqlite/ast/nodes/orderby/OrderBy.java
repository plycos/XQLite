package dev.lycosp.xqlite.ast.nodes.orderby;

import java.util.Collections;
import java.util.List;

public interface OrderBy {
    static List<OrderByNode> emptyOrderBy() {
        return Collections.emptyList();
    }
}
