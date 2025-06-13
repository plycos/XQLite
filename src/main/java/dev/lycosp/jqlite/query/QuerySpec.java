package dev.lycosp.jqlite.query;

import java.util.Collections;
import java.util.List;

public class QuerySpec {
    private final String sql;
    private final List<Object> parameters;

    public static QuerySpec of(String sql) {
        return new QuerySpec(sql, Collections.emptyList());
    }

    public static QuerySpec of(String sql, List<Object> parameters) {
        return new QuerySpec(sql, parameters);
    }

    private QuerySpec(String sql, List<Object> parameters) {
        this.sql = sql;
        this.parameters = parameters;
    }

    public String getSql() {
        return sql;
    }

    public List<Object> getParameters() {
        return parameters;
    }
}
