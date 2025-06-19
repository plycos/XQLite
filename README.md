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

import static dev.lycosp.xqlite.api.XQLite.*;

public class Main {
    public static void main(String[] args) {
        SelectQuery selectQuery = select(
            cols("id", "name"),
            from("users")
        );
        // Prints: SQL: SELECT id, name FROM users
        System.out.println("SQL: " + selectQuery.toSql());
        // Prints: Params: []
        System.out.println("Params: " + selectQuery.getParams());
    }
}