package kev.ui;

import kev.task.Task;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Kev");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showMessage(String message) {
        System.out.println(" " + message);
    }

    public void showLoadingError() {
        System.out.println(" Error loading saved tasks.");
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println(" Noted. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }

    public void showError(String message) {
        System.out.println(" ☹ OOPS!!! " + message);
    }
}



