package kev.command;

import kev.task.TaskList;
import kev.task.Task;
import kev.storage.Storage;
import kev.ui.Ui;
import kev.exception.KevException;
import java.io.IOException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KevException {
        if (index < 0 || index >= tasks.size()) {
            throw new KevException("☹ OOPS!!! The task number is invalid.");
        }

        Task task = tasks.get(index);
        task.markAsDone();
        ui.showTaskMarked(task);

        try {
            storage.saveTasks(tasks.getAll());
        } catch (IOException e) {
            throw new KevException("Error saving tasks: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}


