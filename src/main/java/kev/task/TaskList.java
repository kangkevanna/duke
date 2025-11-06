package kev.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task remove(int index) {
        return deleteTask(index);
    }

    public Task get(int index) {
        return getTask(index);
    }

    public List<Task> getAll() {
        return getAllTasks();
    }

    public List<Task> getTasks() {
        return getAllTasks();
    }

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

