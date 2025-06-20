package dev.lycosp.xqlite.ast.visitor;

import dev.lycosp.xqlite.ast.SqlVisitor;
import dev.lycosp.xqlite.ast.nodes.expression.Expression;
import dev.lycosp.xqlite.runtime.QuerySpec;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VisitorUtils {
    static QuerySpec visitCompositeExpressionNode(List<Expression> expressions, String operator, SqlVisitor<QuerySpec> visitor) {
        Set<Expression> visited = new HashSet<>();
        StringBuilder sqlBuilder = new StringBuilder("(");
        List<Object> params = new ArrayList<>();
        for (int i = 0; i < expressions.size(); i++) {
            Expression expression = expressions.get(i);
            if (!visited.add(expression)) {
                throw new IllegalStateException("Circular reference detected in expression tree: " + expression);
            }
            QuerySpec expressionSpec = visitor.visit(expression);
            sqlBuilder.append(expressionSpec.getSql());
            params.addAll(expressionSpec.getParams());
            if (i < expressions.size() - 1) {
                sqlBuilder.append(" ").append(operator).append(" ");
            }
        }
        sqlBuilder.append(")");
        return QuerySpec.of(sqlBuilder.toString(), params);
    }
}
