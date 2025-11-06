package kev.command;

import kev.task.TaskList;
import kev.ui.Ui;
import kev.storage.Storage;
import kev.exception.KevException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KevException {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

