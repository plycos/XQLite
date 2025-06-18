package dev.lycosp.xqlite;

import dev.lycosp.xqlite.api.SelectQuery;
import dev.lycosp.xqlite.ast.nodes.expression.Expression;

import static dev.lycosp.xqlite.api.XQLite.*;

public class Main {
    public static void main(String[] args) {
        Expression where = and(
                eq("col1", 1),
                ne("col2", 2)
        );
        Expression emptyWhere = Expression.emptyExpression();
        SelectQuery selectQuery = select(
                cols("col1", "col2", "col3"),
                from("table"),
                where(emptyWhere)
        );
        System.out.println(selectQuery.render());
    }
}
