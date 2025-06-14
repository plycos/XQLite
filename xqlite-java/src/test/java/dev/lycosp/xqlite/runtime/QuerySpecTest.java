package dev.lycosp.xqlite.runtime;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class QuerySpecTest {

    @Test
    public void testOfNoParams() {
        QuerySpec qs = QuerySpec.of("SELECT 1");
        assertEquals("SELECT 1", qs.getSql());
        assertTrue(qs.getParams().isEmpty());
    }

    @Test
    public void testOfWithParamsVarargs() {
        QuerySpec qs = QuerySpec.of("SELECT * FROM t WHERE id = ?", 42);
        assertEquals("SELECT * FROM t WHERE id = ?", qs.getSql());
        assertEquals(Collections.singletonList(42), qs.getParams());
    }

    @Test
    public void testOfWithParamsList() {
        List<Object> params = Arrays.asList(1, "foo");
        QuerySpec qs = QuerySpec.of("SELECT * FROM t WHERE a = ? AND b = ?", params);
        assertEquals(params, qs.getParams());
    }

    @Test(expected = NullPointerException.class)
    public void testOfNullSql() {
        QuerySpec.of(null);
    }

    @Test(expected = NullPointerException.class)
    public void testOfNullParamsVarargs() {
        QuerySpec.of("SELECT ?", (Object[]) null);
    }

    @Test(expected = NullPointerException.class)
    public void testOfNullParamsList() {
        QuerySpec.of("SELECT ?", (List<Object>) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParamCountMismatchTooFew() {
        QuerySpec.of("SELECT * FROM t WHERE a = ? AND b = ?", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParamCountMismatchTooMany() {
        QuerySpec.of("SELECT * FROM t WHERE a = ?", 1, 2);
    }

    @Test
    public void testPlaceholderInStringLiteralIgnored() {
        QuerySpec qs = QuerySpec.of("SELECT '?' AS q", new Object[0]);
        assertEquals("SELECT '?' AS q", qs.getSql());
        assertTrue(qs.getParams().isEmpty());
    }

    @Test
    public void testEscapedSingleQuoteInStringLiteral() {
        QuerySpec qs = QuerySpec.of("SELECT '?' AS q, '''?''' AS qq", new Object[0]);
        assertEquals("SELECT '?' AS q, '''?''' AS qq", qs.getSql());
        assertTrue(qs.getParams().isEmpty());
    }

    @Test
    public void testImmutability() {
        List<Object> params = Arrays.asList(1, 2);
        QuerySpec qs = QuerySpec.of("SELECT ?, ?", params);
        try {
            qs.getParams().add(3);
            fail("Params list should be unmodifiable");
        } catch (UnsupportedOperationException ignored) {
        }
    }

    @Test
    public void testDefensiveCopyOfParamsList() {
        List<Object> params = Arrays.asList(1, 2, 3);
        QuerySpec qs = QuerySpec.of("SELECT ?, ?, ?", params);
        params.set(0, 99);
        assertEquals(Arrays.asList(1, 2, 3), qs.getParams());
    }

    @Test
    public void testEqualsAndHashCode() {
        QuerySpec a = QuerySpec.of("SELECT ?", 1);
        QuerySpec b = QuerySpec.of("SELECT ?", 1);
        QuerySpec c = QuerySpec.of("SELECT ?", 2);
        assertEquals(a, b);
        assertNotEquals(a, c);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testToString() {
        QuerySpec qs = QuerySpec.of("SELECT ?", 123);
        String s = qs.toString();
        assertTrue(s.contains("SELECT ?"));
        assertTrue(s.contains("123"));
    }

    @Test
    public void testNullParameterValue() {
        QuerySpec qs = QuerySpec.of("SELECT ? FROM t", (Object) null);
        assertEquals(Collections.singletonList(null), qs.getParams());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySqlString() {
        QuerySpec.of("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhitespaceOnlySql() {
        QuerySpec.of("   ");
    }

    @Test
    public void testLargeParameterList() {
        int n = 1000;
        StringBuilder sql = new StringBuilder("SELECT ");
        Object[] params = new Object[n];
        for (int i = 0; i < n; i++) {
            sql.append("?");
            if (i < n - 1) sql.append(", ");
            params[i] = i;
        }
        QuerySpec qs = QuerySpec.of(sql.toString(), params);
        assertEquals(n, qs.getParams().size());
        for (int i = 0; i < n; i++) {
            assertEquals(i, qs.getParams().get(i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoPlaceholdersButParamsGiven() {
        QuerySpec.of("SELECT 1", 42);
    }
}