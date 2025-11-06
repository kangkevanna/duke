package kev.task;

public class Event extends Task {
    private String at;
    public Event(String description, String at) { super(description); this.at = at; }
    public String toFileString() { return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + at; }
    public String toString() { return "[E][" + getStatusIcon() + "] " + description + " (at: " + at + ")"; }
}
