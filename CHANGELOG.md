# Changelog

## [minor/expressions] - Feature Release (v0.2.0)

### Added
- Implemented basic expression handling in `SqlVisitor` and `SelectRenderVisitor` for SQL query generation.
- Support for logical expressions (`AND`, `OR`) and comparison operators (`=`, `<`, `>`, `<=`, `>=`, `<>`) in SQL queries.
- Support for `WHERE` clauses in SELECT queries.
- Added `Expression.emptyExpression()` for cases where no `WHERE` clause is needed. This can be used to explicitly define an empty condition when dynamically generating queries. Alternatively, if no `WHERE` clause is required, it can simply be omitted.

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