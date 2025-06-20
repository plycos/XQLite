# XQLite Java

A lightweight, type-safe SQL query builder for Java.  
Compose SQL queries using a fluent, immutable API that abstracts away AST details and provides convenient rendering and parameterization.

> **Note:** XQLite Java is an early-stage project and is **not a fully featured SQL builder**. It currently supports a limited subset of SQL and may lack advanced features found in mature libraries.

## Roadmap

- [X] WHERE clauses w/ basic expressions
- [X] Order By
- [ ] JOIN support
- [ ] Pagination
- [ ] Advanced expressions and function support
- [ ] Comprehensive test coverage
- [ ] Documentation improvements
- [ ] ResultSet mapping (unsure)
- [ ] Compile time safety (unsure)

## Features

- Immutable, thread-safe query objects
- SQL AST nodes with type-safety goals (WIP)
- Lisp like API for building queries
- Convenient rendering to SQL and parameter lists

## Example Usage

```java
import dev.lycosp.xqlite.api.SelectQuery;
import dev.lycosp.xqlite.ast.nodes.expression.AndNode;
import dev.lycosp.xqlite.ast.nodes.expression.Expression;
import dev.lycosp.xqlite.ast.nodes.orderby.OrderBy;
import dev.lycosp.xqlite.ast.nodes.orderby.OrderByNode;
import dev.lycosp.xqlite.runtime.QuerySpec;

import java.util.Collections;
import java.util.List;

import static dev.lycosp.xqlite.api.XQLite.*;

public class Main {
    public static void main(String[] args) {
        List<Expression> whereExpressions1 = Collections.singletonList(eq("status", "active"));
        List<OrderByNode> orderByNodes1 = Collections.singletonList(asc("name"));
        System.out.println(generateDynamicQuery(whereExpressions1, orderByNodes1));
        // Output: QuerySpec{sql='SELECT id, name, age, status FROM users WHERE (status = ?) ORDER BY name ASC', params=[active]}

        List<Expression> whereExpressions2 = Collections.emptyList();
        List<OrderByNode> orderByNodes2 = Collections.emptyList();
        System.out.println(generateDynamicQuery(whereExpressions2, orderByNodes2));
        // Output: QuerySpec{sql='SELECT id, name, age, status FROM users', params=[]}
    }

    public static QuerySpec generateDynamicQuery(List<Expression> whereExpressions, List<OrderByNode> orderBys) {
        // Generate WHERE clause
        Expression whereClause = whereExpressions.isEmpty()
                ? Expression.emptyExpression()
                : AndNode.and(whereExpressions);

        // Generate ORDER BY clause
        List<OrderByNode> orderByClause = orderBys.isEmpty()
                ? OrderBy.emptyOrderBy()
                : orderBys;

        // Build the query
        SelectQuery selectQuery = select(
                cols("id", "name", "age", "status"),
                from("users"),
                where(whereClause),
                orderBy(orderByClause)
        );

        return selectQuery.render();
    }
}
```