package dev.lycosp.xqlite.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListUtilsTest {

    @Test
    void immutableCopyReturnsUnmodifiableList() {
        List<String> original = Arrays.asList("a", "b", "c");
        List<String> copy = ListUtils.immutableCopy(original);

        assertEquals(original, copy);
        assertThrows(UnsupportedOperationException.class, () -> copy.add("d"));
    }

    @Test
    void immutableCopyIsIndependentOfOriginal() {
        List<String> original = new ArrayList<>(Arrays.asList("x", "y"));
        List<String> copy = ListUtils.immutableCopy(original);

        original.add("z");
        assertEquals(Arrays.asList("x", "y"), copy);
        assertEquals(Arrays.asList("x", "y", "z"), original);
    }

    @Test
    void immutableCopyEmptyCollection() {
        List<String> copy = ListUtils.immutableCopy(Collections.emptyList());
        assertTrue(copy.isEmpty());
        assertThrows(UnsupportedOperationException.class, () -> copy.add("foo"));
    }

    @Test
    void immutableCopyNullCollectionThrowsException() {
        assertThrows(NullPointerException.class, () -> ListUtils.immutableCopy(null));
    }
}