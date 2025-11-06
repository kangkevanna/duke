package kev.command;

import kev.task.TaskList;
import kev.storage.Storage;
import kev.ui.Ui;
import kev.task.Task;
import kev.task.Todo;
import kev.task.Event;
import kev.task.Deadline;
import kev.exception.KevException;
import java.io.IOException;

public class AddCommand extends Command {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KevException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new KevException("☹ OOPS!!! The description of a task cannot be empty.");
        }

        String command = parts[0];
        String description = parts[1];
        Task task;

        switch (command) {
            case "todo":
                task = new Todo(description);
                break;
            case "deadline":
                String[] deadlineParts = description.split(" /by ");
                task = new Deadline(deadlineParts[0], deadlineParts[1]);
                break;
            case "event":
                String[] eventParts = description.split(" /at ");
                task = new Event(eventParts[0], eventParts[1]);
                break;
            default:
                throw new KevException("☹ OOPS!!! Unknown add command.");
        }

        tasks.addTask(task);
        ui.showMessage("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");

        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (IOException e) {
            throw new KevException("Error saving tasks: " + e.getMessage());
        }
    }
}


