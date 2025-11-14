package kev.command;

import kev.task.Task;
import kev.task.TaskList;
import kev.ui.Ui;
import kev.storage.Storage;
import kev.exception.KevException;
import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KevException {
        if (index < 0 || index >= tasks.size()) {
            throw new KevException("☹ OOPS!!! The task number is invalid.");
        }

        Task removed = tasks.deleteTask(index);
        ui.showTaskDeleted(removed, tasks.size());
        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (IOException e) {
            throw new KevException("Error saving tasks: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
