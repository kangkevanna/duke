package kev.ui;

import kev.task.Task;
import java.util.Scanner;

/**
 * handles all interactions with the user.
 * displays messages, reads commands, and prints task information.
 */
public class Ui {
    /** scanner used to read user input from standard input. */
    private Scanner sc;

    /** creates a new UI object and initializes the input scanner. */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * reads the next user command from input.
     *
     * @return The trimmed user input string.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /** displays the welcome message shown when the program starts. */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Kev");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /** prints the horizontal separator line. */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * displays a generic message.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(" " + message);
    }

    /** displays an error message when loading saved tasks fails. */
    public void showLoadingError() {
        System.out.println(" Error loading saved tasks.");
    }

    /**
     * displays confirmation message for adding a task.
     *
     * @param task The task added.
     * @param size The new total number of tasks.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println(" Noted. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    /**
     * displays confirmation message for deleting a task.
     *
     * @param task The task deleted.
     * @param size The new total number of tasks.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    /**
     * displays confirmation message for marking a task as done.
     *
     * @param task The task that was marked done.
     */
    public void showTaskMarked(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    /**
     * displays confirmation message for marking a task as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }

    /**
     * displays error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(" ☹ OOPS!!! " + message);
    }
}



