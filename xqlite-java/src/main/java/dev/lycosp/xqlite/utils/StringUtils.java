package dev.lycosp.xqlite.utils;

/**
 * Utility class for common String operations.
 *
 * <p>
 * Repository: <a href="https://github.com/plycos/xqlite">https://github.com/plycos/xqlite</a>
 * </p>
 *
 * <p>
 * This class provides static helper methods for string validation and analysis,
 * such as checking for blank strings and enforcing non-blank arguments.
 * </p>
 *
 * <h2>Example Usage</h2>
 * <pre>{@code
 * // Check if a string is blank
 * boolean blank = StringUtils.isBlank("   "); // true
 *
 * // Check if a string is null
 * boolean isNull = StringUtils.isBlank(null); // true
 *
 * // Validate a string is not blank or null
 * String value = StringUtils.requireNonBlank("foo", "Value must not be blank");
 * // Throws IllegalArgumentException if the string is null, empty, or whitespace only
 *
 * // This will throw an exception:
 * StringUtils.requireNonBlank(null, "Value must not be blank");
 * }</pre>
 *
 * @author <a href="https://github.com/plycos">Peter Lycos (@plycos)</a>
 */
public class StringUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private StringUtils() {
    }

    /**
     * Checks if a string is {@code null}, empty, or contains only whitespace characters.
     *
     * @param s the string to check
     * @return {@code true} if the string is {@code null}, empty, or whitespace only; {@code false} otherwise
     */
    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    /**
     * Ensures that a string is not {@code null}, empty, or whitespace only.
     * Throws an {@link IllegalArgumentException} if the check fails.
     *
     * @param s       the string to validate
     * @param message the exception message to use if the string is invalid
     * @return the validated string if it is not blank
     * @throws IllegalArgumentException if the string is {@code null}, empty, or whitespace only
     */
    public static String requireNonBlank(String s, String message) {
        if (isBlank(s)) {
            throw new IllegalArgumentException(message);
        }
        return s;
    }
}