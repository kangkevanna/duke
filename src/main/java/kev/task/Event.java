package kev.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * an event task that occurs on a specific date.
 */
public class Event extends Task {

    /** date the event occurs at. */
    private LocalDate at;

    /**
     * creates a new Event task.
     *
     * @param description Description of the event.
     * @param at The event date in YYYY-MM-DD format.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at); // expects yyyy-MM-dd format
    }

    /**
     * returns the event date.
     *
     * @return The event date as a LocalDate.
     */
    public LocalDate getAt() {
        return at;
    }

    /** sets/updates task with new date for deadline. */
    public void setAt(LocalDate at) {
        this.at = at;
    }

    /**
     * converts the event into a savable string format for file storage.
     *
     * @return Formatted string representing the event.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + at;
    }

    /**
     * returns a more easily referenced/readable representation of the event task.
     *
     * @return Display string for the event task.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}