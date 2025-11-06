package kev.task;

public class Deadline extends Task {
    private String by;
    public Deadline(String description, String by) { super(description); this.by = by; }
    public String toFileString() { return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by; }
    public String toString() { return "[D][" + getStatusIcon() + "] " + description + " (by: " + by + ")"; }
}
