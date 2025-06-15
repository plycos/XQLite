package dev.lycosp.xqlite.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void isBlankReturnsTrueForNull() {
        assertTrue(StringUtils.isBlank(null));
    }

    @Test
    void isBlankReturnsTrueForEmptyString() {
        assertTrue(StringUtils.isBlank(""));
    }

    @Test
    void isBlankReturnsTrueForWhitespaceOnly() {
        assertTrue(StringUtils.isBlank("   \t\n"));
    }

    @Test
    void isBlankReturnsFalseForNonBlankString() {
        assertFalse(StringUtils.isBlank("foo"));
    }

    @Test
    void isBlankReturnsFalseForStringWithWhitespaceAndText() {
        assertFalse(StringUtils.isBlank("  bar  "));
    }

    @Test
    void requireNonBlankReturnsStringIfNotBlank() {
        String s = "baz";
        assertEquals(s, StringUtils.requireNonBlank(s, "Should not throw"));
    }

    @Test
    void requireNonBlankThrowsForNull() {
        assertThrows(IllegalArgumentException.class, () -> StringUtils.requireNonBlank(null, "Null not allowed"));
    }

    @Test
    void requireNonBlankThrowsForEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> StringUtils.requireNonBlank("", "Empty not allowed"));
    }

    @Test
    void requireNonBlankThrowsForWhitespaceOnly() {
        assertThrows(IllegalArgumentException.class, () -> StringUtils.requireNonBlank("   ", "Blank not allowed"));
    }
}