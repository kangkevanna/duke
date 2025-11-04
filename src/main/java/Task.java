public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        String typeLabel = switch (type) {
            case TODO -> "[T]";
            case DEADLINE -> "[D]";
            case EVENT -> "[E]";
        };
        return typeLabel + "[" + getStatusIcon() + "] " + description;
    }
}