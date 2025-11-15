package kev;

import kev.storage.Storage;
import kev.ui.Ui;
import kev.task.TaskList;
import kev.command.Command;
import kev.exception.KevException;

/**
 * main Kev application class responsible for initializing
 * and running the application.
 */
public class Kev {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String DATA_PATH = "data/duke.txt";

    /**
     * creates a new instance of the Kev application.
     * initializes the UI, storage, and task list.
     * loads the tasks from the file specified by the filePath.
     *
     * @param filePath The path of the file where tasks are stored.
     */
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

    /**
     * runs the main event loop of the application.
     * waits for user input and executes the corresponding command.
     */
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

    /**
     * main method that starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Kev(DATA_PATH).run();
    }
}