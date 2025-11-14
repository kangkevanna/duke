package kev.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Converts LocalDateTime to storage format string.
     */
    public static String toStorageFormat(LocalDateTime dateTime) {
        return dateTime.format(STORAGE_FORMAT);
    }

    /**
     * Converts LocalDateTime to user-display format string.
     */
    public static String toDisplayFormat(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_FORMAT);
    }

    /**
     * Parses a date string from storage format to LocalDateTime.
     */
    public static LocalDateTime parseStoredFormat(String str) {
        return LocalDateTime.parse(str, STORAGE_FORMAT);
    }

    /**
     * Parses natural language date strings (e.g., "June 6th 18:00") to LocalDateTime.
     * Implement your natural language parsing here.
     */
    public static LocalDateTime parseNaturalDate(String str) {
        try {
            // Example: "June 6th 18:00"
            // For simplicity, we can use "MMM d HH:mm" and append the current year
            int year = LocalDateTime.now().getYear();
            String cleaned = str.replaceAll("(st|nd|rd|th)", ""); // remove suffix
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d HH:mm yyyy");
            return LocalDateTime.parse(cleaned + " " + year, formatter);
        } catch (DateTimeParseException e) {
            // fallback to now if parsing fails
            return LocalDateTime.now();
        }
    }
}

