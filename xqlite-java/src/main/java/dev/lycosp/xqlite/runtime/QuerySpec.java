package dev.lycosp.xqlite.runtime;

import java.util.*;

/**
 * Immutable, thread-safe value object representing a SQL query and its parameters.
 * <p>
 * Use {@link #of(String)}, {@link #of(String, Object...)}, or {@link #of(String, List)} to create instances.
 * The parameter list is always unmodifiable and never {@code null}.
 * <p>
 * This class is suitable for safely sharing query specifications between threads.
 * </p>
 *
 * <pre>
 * {@code
 * QuerySpec noParams = QuerySpec.of("SELECT * FROM users");
 * System.out.println(noParams);
 * // Output: QuerySpec{sql='SELECT * FROM users', params=[]}
 *
 * QuerySpec withParams = QuerySpec.of("SELECT * FROM users WHERE id = ?", 42);
 * System.out.println(withParams);
 * // Output: QuerySpec{sql='SELECT * FROM users WHERE id = ?', params=[42]}
 *
 * QuerySpec withList = QuerySpec.of("SELECT * FROM users WHERE id = ?", Arrays.asList(42));
 * System.out.println(withList);
 * // Output: QuerySpec{sql='SELECT * FROM users WHERE id = ?', params=[42]}
 *
 * // Java 9+:
 * QuerySpec withList9 = QuerySpec.of("SELECT * FROM users WHERE id = ?", List.of(42));
 * System.out.println(withList9);
 * // Output: QuerySpec{sql='SELECT * FROM users WHERE id = ?', params=[42]}
 * }
 * </pre>
 *
 * <p>
 * Repository: <a href="https://github.com/plycos/xqlite">https://github.com/plycos/xqlite</a>
 * </p>
 *
 * @author <a href="https://github.com/plycos">Peter Lycos (@plycos)</a>
 */
public final class QuerySpec {

    /**
     * The SQL statement to execute.
     */
    private final String sql;

    /**
     * The parameters to bind to the SQL statement.
     */
    private final List<Object> params;

    /**
     * Creates a {@code QuerySpec} for a SQL statement with no parameters.
     *
     * @param sql the SQL statement (must not be {@code null}, empty, or whitespace only)
     * @return a new {@code QuerySpec} instance
     * @throws NullPointerException     if {@code sql} is {@code null}
     * @throws IllegalArgumentException if {@code sql} is empty or whitespace only
     */
    public static QuerySpec of(String sql) {
        return new QuerySpec(sql, Collections.emptyList());
    }

    /**
     * Creates a {@code QuerySpec} for a SQL statement with the given parameters.
     * <p>
     * This overload is convenient for Java 8 users, allowing parameters to be specified directly.
     * </p>
     *
     * @param sql    the SQL statement (must not be {@code null}, empty, or whitespace only)
     * @param params the parameters to bind (must not be {@code null}, may be empty)
     * @return a new {@code QuerySpec} instance
     * @throws NullPointerException     if {@code sql} or {@code params} is {@code null}
     * @throws IllegalArgumentException if {@code sql} is empty or whitespace only
     */
    public static QuerySpec of(String sql, Object... params) {
        return new QuerySpec(sql, Arrays.asList(params));
    }

    /**
     * Creates a {@code QuerySpec} for a SQL statement with the given parameter list.
     *
     * @param sql    the SQL statement (must not be {@code null}, empty, or whitespace only)
     * @param params the parameters to bind (must not be {@code null})
     * @return a new {@code QuerySpec} instance
     * @throws NullPointerException     if {@code sql} or {@code params} is {@code null}
     * @throws IllegalArgumentException if {@code sql} is empty or whitespace only
     */
    public static QuerySpec of(String sql, List<Object> params) {
        return new QuerySpec(sql, params);
    }

    /**
     * Constructs a new {@code QuerySpec}.
     *
     * @param sql    the SQL statement (must not be {@code null}, empty, or whitespace only)
     * @param params the parameters to bind (must not be {@code null})
     * @throws NullPointerException     if {@code sql} or {@code params} is {@code null}
     * @throws IllegalArgumentException if {@code sql} is empty or whitespace only
     */
    private QuerySpec(String sql, List<Object> params) {
        this.sql = Objects.requireNonNull(sql, "sql must not be null");
        if (sql.trim().isEmpty()) {
            throw new IllegalArgumentException("SQL must not be empty or whitespace only");
        }
        this.params = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(params, "params must not be null")));
        int placeholderCount = countSqlPlaceholders(this.sql);
        if (placeholderCount != this.params.size()) {
            // @formatter:off
            throw new IllegalArgumentException(
                    "SQL expects " + placeholderCount + " parameter(s)" +
                    ", but got " + this.params.size() + " for " + this.toString()
            );
            // @formatter:on
        }
    }

    /**
     * Counts the number of positional parameter placeholders ('?') in the given SQL statement,
     * ignoring any that appear inside single-quoted string literals.
     *
     * @param sql the SQL statement to analyze (must not be {@code null})
     * @return the number of positional parameter placeholders outside string literals
     */
    private static int countSqlPlaceholders(String sql) {
        int count = 0;
        boolean inSingleQuote = false;
        for (int i = 0; i < sql.length(); i++) {
            char c = sql.charAt(i);
            if (c == '\'') {
                if (inSingleQuote && i + 1 < sql.length() && sql.charAt(i + 1) == '\'') {
                    // Escaped single quote inside string literal, skip next character
                    i++;
                } else {
                    inSingleQuote = !inSingleQuote;
                }
            } else if (c == '?' && !inSingleQuote) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the SQL statement.
     *
     * @return the SQL statement (never {@code null})
     */
    public String getSql() {
        return sql;
    }

    /**
     * Returns the parameters to bind to the SQL statement.
     *
     * @return an unmodifiable list of parameters (never {@code null})
     */
    public List<Object> getParams() {
        return params;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is the same as the {@code o} argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuerySpec querySpec = (QuerySpec) o;
        return Objects.equals(sql, querySpec.sql) && Objects.equals(params, querySpec.params);
    }

    /**
     * Returns a hash code value for this object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(sql, params);
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "QuerySpec{" + "sql='" + sql + '\'' + ", params=" + params + '}';
    }
}