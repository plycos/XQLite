package dev.lycosp.xqlite.ast.nodes.orderby;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.utils.ListUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class OrderByNodes implements SqlNode {
    private final List<OrderByNode> nodes;

    public static OrderByNodes orderBy(OrderByNode... nodes) {
        return new OrderByNodes(ListUtils.immutableCopy(Arrays.asList(nodes)));
    }

    public static OrderByNodes orderBy(List<OrderByNode> nodes) {
        return new OrderByNodes(ListUtils.immutableCopy(nodes));
    }

    private OrderByNodes(List<OrderByNode> nodes) {
        this.nodes = Objects.requireNonNull(nodes, "Nodes cannot be null");
    }

    public List<OrderByNode> getNodes() {
        return nodes;
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    @Override
    public <R> R accept(SqlVisitor<R> visitor) {
        return visitor.visitOrderBys(this);
    }

    @Override
    public String toString() {
        return "OrderByNodes{" +
                "nodes=" + nodes +
                '}';
    }
}