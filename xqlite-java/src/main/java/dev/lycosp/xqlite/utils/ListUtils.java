package dev.lycosp.xqlite.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Utility methods for working with {@link List} instances.
 *
 * <p>
 * Repository: <a href="https://github.com/plycos/xqlite">https://github.com/plycos/xqlite</a>
 * </p>
 *
 * <p>
 * This class provides methods to create immutable copies of lists.
 * </p>
 *
 * <h2>Example Usage</h2>
 * <pre>{@code
 * List<String> original = new ArrayList<>();
 * original.add("foo");
 * original.add("bar");
 * List<String> copy = ListUtils.immutableCopy(original);
 * // copy is unmodifiable and changes to original do not affect copy
 * }</pre>
 *
 * @author <a href="https://github.com/plycos">Peter Lycos (@plycos)</a>
 */
public class ListUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private ListUtils() {
    }

    /**
     * Returns an unmodifiable copy of the given collection as a {@link List}.
     * <p>
     * The returned list is a new {@link ArrayList} containing the elements of the input collection,
     * wrapped with {@link Collections#unmodifiableList(List)} to prevent modification.
     * </p>
     *
     * @param elements the collection whose elements are to be placed into the new list
     * @param <T>      the type of elements in the list
     * @return an unmodifiable list containing the elements of the specified collection
     * @throws NullPointerException if the specified collection is null
     */
    public static <T> List<T> immutableCopy(Collection<? extends T> elements) {
        return Collections.unmodifiableList(new ArrayList<>(elements));
    }
}