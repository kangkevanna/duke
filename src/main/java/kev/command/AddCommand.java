package kev.command;

import kev.task.*;
import kev.storage.Storage;
import kev.ui.Ui;
import kev.exception.KevException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KevException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) throw new KevException("The description of a task cannot be empty.");

        String command = parts[0];
        String description = parts[1];
        Task task;

        switch (command) {
            case "todo":
                task = new Todo(description);
                break;

            case "deadline":
                String[] deadlineParts = description.split(" /by ");
                if (deadlineParts.length != 2)
                    throw new KevException("Deadline must have a date: /by YYYY-MM-DD");
                try {
                    LocalDate by = LocalDate.parse(deadlineParts[1].trim());
                    task = new Deadline(deadlineParts[0].trim(), by.toString());
                } catch (DateTimeParseException e) {
                    throw new KevException("Invalid date format! Use YYYY-MM-DD");
                }
                break;

            case "event":
                String[] eventParts = description.split(" /at ");
                if (eventParts.length != 2)
                    throw new KevException("Event must have a date: /at YYYY-MM-DD");
                try {
                    LocalDate at = LocalDate.parse(eventParts[1].trim());
                    task = new Event(eventParts[0].trim(), at.toString());
                } catch (DateTimeParseException e) {
                    throw new KevException("Invalid date format! Use YYYY-MM-DD");
                }
                break;

            default:
                throw new KevException("Unknown add command.");
        }

        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());

        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (IOException e) {
            throw new KevException("Error saving tasks: " + e.getMessage());
        }
    }
}
