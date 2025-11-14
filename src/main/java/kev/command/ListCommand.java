package kev.command;

import kev.task.TaskList;
import kev.ui.Ui;
import kev.storage.Storage;
import kev.exception.KevException;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KevException {
        ui.showLine();
        if (tasks.size() == 0) {
            ui.showMessage("Your task list is empty!");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.getAll().get(i));
            }
        }
        ui.showLine();
    }
}
