package dev.lycosp.xqlite.utils;

/**
 * Utility class for SQL-related helper methods.
 *
 * <p>
 * Repository: <a href="https://github.com/plycos/xqlite">https://github.com/plycos/xqlite</a>
 * </p>
 *
 * <p>
 * This class provides static methods for analyzing and manipulating SQL statements.
 * </p>
 *
 * <h2>Example Usage</h2>
 * <pre>{@code
 * String sql = "SELECT * FROM users WHERE name = ? AND status = 'active' AND note = '?' AND age = ?";
 * int placeholderCount = SqlUtils.countSqlPlaceholders(sql);
 * System.out.println("Number of placeholders: " + placeholderCount); // Output: 2
 * }</pre>
 *
 * @author <a href="https://github.com/plycos">Peter Lycos (@plycos)</a>
 */
public class SqlUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private SqlUtils() {
    }

    /**
     * Counts the number of positional parameter placeholders ({@code '?'}) in the given SQL statement,
     * ignoring any that appear inside single-quoted string literals.
     *
     * <p>
     * This method is useful for determining how many parameters need to be bound to a prepared statement.
     * Escaped single quotes within string literals are handled correctly.
     * </p>
     *
     * @param sql the SQL statement to analyze (must not be {@code null})
     * @return the number of positional parameter placeholders outside string literals
     * @throws NullPointerException if {@code sql} is {@code null}
     */
    public static int countSqlPlaceholders(String sql) {
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
}