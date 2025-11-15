package kev.task;

import java.util.ArrayList;
import java.util.List;

/**
 * a list of tasks and the operations
 * to modify and retrieve tasks.
 */
public class TaskList {

    /** internal list storing all tasks. */
    private ArrayList<Task> tasks;

    /** creates an empty TaskList. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * creates a TaskList initialized with existing tasks.
     *
     * @param tasks A list of tasks to populate the task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * adds a new task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * deletes task of the specified index.
     *
     * @param index Index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * retrieves task by index.
     *
     * @param index Index of the desired task.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * returns the number of tasks.
     *
     * @return Task count.
     */
    public int size() {
        return tasks.size();
    }

    /** marks a task as done. */
    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    /** mrks a task as not done. */
    public void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
    }

    /**
     * returns all tasks in the list.
     *
     * @return List of tasks.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }

    /** same as deleteTask(): removes a task. */
    public Task remove(int index) {
        return deleteTask(index);
    }

    /** alias for getTask(). */
    public Task get(int index) {
        return getTask(index);
    }

    /** Alias for getAllTasks(). */
    public List<Task> getAll() {
        return getAllTasks();
    }

    /** alias for getAllTasks(). */
    public List<Task> getTasks() {
        return getAllTasks();
    }

    /**
     * prints all tasks to standard output.
     * (only used internally for debugging.)
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks in your list!");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
}
