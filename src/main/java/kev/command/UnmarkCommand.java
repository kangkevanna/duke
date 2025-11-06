package kev.command;

import kev.task.TaskList;
import kev.task.Task;
import kev.ui.Ui;
import kev.storage.Storage;
import kev.exception.KevException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KevException {
        if (index < 0 || index >= tasks.size()) {
            throw new KevException("☹ OOPS!!! The task number is invalid.");
        }

        Task task = tasks.get(index);
        task.markAsNotDone();
        ui.showTaskUnmarked(task);

        try {
            storage.saveTasks(tasks.getAllTasks());
        } catch (Exception e) {
            throw new KevException("Error saving tasks: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}


