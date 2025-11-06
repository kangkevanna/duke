package kev.command;

import kev.task.TaskList;
import kev.ui.Ui;
import kev.storage.Storage;
import kev.exception.KevException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KevException;
    public boolean isExit() {
        return false;
    }
}

