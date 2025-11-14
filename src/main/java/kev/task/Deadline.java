package kev.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by; // store date as LocalDate

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by); // expects yyyy-MM-dd format
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
