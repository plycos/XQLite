# Changelog

## [patch/fix-columnnodes] - Refactor of ColumnsNode (v0.3.1)
### Changed
- Added `visitColumns` method to `SqlVisitor` to handle `ColumnsNode`.
- Moved logic to render `ColumnsNode` from `VisitorUtils` to `SelectRenderVisitor`.

### Removed
- Removed `generateColumnsSql` method from `VisitorUtils`.

## [minor/orderby] - Feature Release (v0.3.0)
### Added
- Support for `ORDER BY` clauses in query building.

### Changed
- Improved SQL rendering and parameterization.

### Upcoming
- Refactor internal Expression logic to reduce number of visitor methods.

### Example
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
        // Output: SELECT id, name, age, status FROM users WHERE status = ? ORDER BY name ASC;

        List<Expression> whereExpressions2 = Collections.emptyList();
        List<OrderByNode> orderByNodes2 = Collections.emptyList();
        System.out.println(generateDynamicQuery(whereExpressions2, orderByNodes2));
        // Output: SELECT id, name, age, status FROM users;
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

        return selectQuery.render().toSql();
    }
}
```

---

## [patch/update-readme] - Readme Update (v0.2.1)

### Added
- Marked WHERE clauses w/ basic expressions as complete on roadmap

---

## [minor/expressions] - Feature Release (v0.2.0)

### Added
- Implemented basic expression handling in `SqlVisitor` and `SelectRenderVisitor` for SQL query generation.
- Support for logical expressions (`AND`, `OR`) and comparison operators (`=`, `<`, `>`, `<=`, `>=`, `<>`) in SQL queries.
- Support for `WHERE` clauses in SELECT queries.
- Added `Expression.emptyExpression()` for cases where no `WHERE` clause is needed. This can be used to explicitly define an empty condition when dynamically generating queries. Alternatively, if no `WHERE` clause is required, it can simply be omitted.

### Removed
- `Main.java` that existed for testing purposes.

### Example
```java
import dev.lycosp.xqlite.api.SelectQuery;
import dev.lycosp.xqlite.ast.nodes.expression.Expression;

import static dev.lycosp.xqlite.api.XQLite.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(generateDynamicQuery(true, "age", 18, "status", "active"));
        // Output: SELECT id, name, age, status FROM users WHERE age = 18 AND status = 'active';

        System.out.println(generateDynamicQuery(false, "age", 18, "status", "active"));
        // Output: SELECT id, name, age, status FROM users;
    }

    public static String generateDynamicQuery(boolean includeWhere, String col1, Object val1, String col2, Object val2) {
        Expression whereClause = includeWhere
                ? and(eq(col1, val1), eq(col2, val2))
                : Expression.emptyExpression();

        SelectQuery selectQuery = select(
                cols("id", "name", "age", "status"),
                from("users"),
                where(whereClause)
        );

        return selectQuery.render();
    }
}
---