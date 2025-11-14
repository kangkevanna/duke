package kev.command;

import kev.task.*;
import kev.ui.Ui;
import kev.storage.Storage;
import kev.exception.KevException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class OnCommand extends Command {
    private String dateStr;

    public OnCommand(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KevException {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            List<Task> all = tasks.getAllTasks();
            boolean found = false;
            for (Task t : all) {
                if (t instanceof Deadline && ((Deadline) t).getBy().equals(date)) {
                    ui.showMessage(t.toString());
                    found = true;
                } else if (t instanceof Event && ((Event) t).getAt().equals(date)) {
                    ui.showMessage(t.toString());
                    found = true;
                }
            }
            if (!found) ui.showMessage("No tasks found on " + date);
        } catch (DateTimeParseException e) {
            throw new KevException("Invalid date format! Use YYYY-MM-DD");
        }
    }
}