package dev.lycosp.xqlite.api;

import dev.lycosp.xqlite.ast.SqlNode;
import dev.lycosp.xqlite.ast.SqlRenderer;
import dev.lycosp.xqlite.ast.nodes.select.SelectBuilder;
import dev.lycosp.xqlite.ast.nodes.select.SelectNode;
import dev.lycosp.xqlite.runtime.QuerySpec;

import java.util.List;

/**
 * Immutable, thread-safe wrapper for building and rendering SQL SELECT queries.
 *
 * <p>
 * Repository: <a href="https://github.com/plycos/xqlite">https://github.com/plycos/xqlite</a>
 * </p>
 *
 * <p>
 * Encapsulates a {@link SelectNode} AST and provides convenient methods to render SQL and retrieve parameters.
 * Caches the rendered {@link QuerySpec} for efficiency.
 * </p>
 *
 * <pre>
 * {@code
 * SelectQuery query = SelectQuery.select(
 *     cols("id", "name"),
 *     from("users")
 * );
 * String sql = query.toSql();
 * List<Object> params = query.getParams();
 * }
 * </pre>
 *
 * @author <a href="https://github.com/plycos">Peter Lycos (@plycos)</a>
 */
public class SelectQuery {
    private final SelectNode node;
    private QuerySpec cachedSpec;

    /**
     * Creates a new {@code SelectQuery} from the given SQL AST nodes.
     *
     * @param args the SQL AST nodes representing the SELECT statement parts
     * @return a new {@code SelectQuery} instance
     */
    public static SelectQuery select(SqlNode... args) {
        return new SelectQuery(SelectBuilder.select(args));
    }

    /**
     * Constructs a {@code SelectQuery} from a {@link SelectNode}.
     *
     * @param node the select AST node
     */
    private SelectQuery(SelectNode node) {
        this.node = node;
    }

    /**
     * Renders the SQL and parameters for this query, caching the result for future calls.
     *
     * @return the rendered {@link QuerySpec}
     */
    public QuerySpec render() {
        if (cachedSpec == null) {
            cachedSpec = SqlRenderer.render(node);
        }
        return cachedSpec;
    }

    /**
     * Returns the SQL string for this query.
     *
     * @return the SQL statement
     */
    public String toSql() {
        return render().getSql();
    }

    /**
     * Returns the parameters to bind to the SQL statement.
     *
     * @return an unmodifiable list of parameters
     */
    public List<Object> getParams() {
        return render().getParams();
    }
}