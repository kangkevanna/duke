package kev.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at); // expects yyyy-MM-dd format
    }

    public LocalDate getAt() {
        return at;
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + at;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}