package dev.lycosp.xqlite.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqlUtilsTest {

    @Test
    void countSqlPlaceholdersReturnsCorrectCount() {
        assertEquals(2, SqlUtils.countSqlPlaceholders("SELECT * FROM t WHERE a = ? AND b = ?"));
    }

    @Test
    void countSqlPlaceholdersReturnsZeroWhenNoPlaceholders() {
        assertEquals(0, SqlUtils.countSqlPlaceholders("SELECT 1"));
    }

    @Test
    void countSqlPlaceholdersIgnoresPlaceholdersInStringLiterals() {
        assertEquals(1, SqlUtils.countSqlPlaceholders("SELECT '?' AS q, b FROM t WHERE c = ?"));
    }

    @Test
    void countSqlPlaceholdersHandlesEscapedSingleQuotes() {
        assertEquals(1, SqlUtils.countSqlPlaceholders("SELECT '''?''' AS qq, b FROM t WHERE c = ?"));
    }

    @Test
    void countSqlPlaceholdersHandlesMultipleStringLiterals() {
        assertEquals(2, SqlUtils.countSqlPlaceholders("SELECT '?' AS q, '?' AS qq, a, b FROM t WHERE x = ? AND y = ?"));
    }

    @Test
    void countSqlPlaceholdersReturnsZeroForEmptyString() {
        assertEquals(0, SqlUtils.countSqlPlaceholders(""));
    }

    @Test
    void countSqlPlaceholdersReturnsZeroForOnlyStringLiteral() {
        assertEquals(0, SqlUtils.countSqlPlaceholders("'?'"));
    }

    @Test
    void countSqlPlaceholdersCountsAllPlaceholders() {
        assertEquals(3, SqlUtils.countSqlPlaceholders("?,?,?"));
    }

    @Test
    void countSqlPlaceholdersHandlesUnclosedStringLiteral() {
        assertEquals(1, SqlUtils.countSqlPlaceholders("SELECT '?' AS q, b FROM t WHERE c = ? AND d = '?'"));
    }

    @Test
    void countSqlPlaceholdersThrowsOnNullSql() {
        assertThrows(NullPointerException.class, () -> SqlUtils.countSqlPlaceholders(null));
    }
}