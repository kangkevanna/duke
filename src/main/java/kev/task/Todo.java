package kev.task;

public class Todo extends Task {
    public Todo(String description) { super(description); }
    public String toFileString() { return "T | " + (isDone ? "1" : "0") + " | " + description; }
    public String toString() { return "[T][" + getStatusIcon() + "] " + description; }
}