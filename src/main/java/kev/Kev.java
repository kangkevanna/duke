package kev;

import kev.storage.Storage;
import kev.ui.Ui;
import kev.task.TaskList;
import kev.command.Command;
import kev.exception.KevException;

public class Kev {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String DATA_PATH = "data/duke.txt";

    public Kev(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (KevException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KevException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Kev(DATA_PATH).run();
    }
}