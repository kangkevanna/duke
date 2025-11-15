package kev.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void addTask_increasesSize() {
        TaskList tasks = new TaskList();
        Task t = new Todo("Read book");
        tasks.addTask(t);
        assertEquals(1, tasks.size());
    }

    @Test
    void deleteTask_removesCorrectTask() {
        TaskList tasks = new TaskList();
        Task t1 = new Todo("Task 1");
        Task t2 = new Todo("Task 2");
        tasks.addTask(t1);
        tasks.addTask(t2);

        Task removed = tasks.deleteTask(0);
        assertEquals(t1, removed);
        assertEquals(1, tasks.size());
        assertEquals(t2, tasks.getTask(0));
    }
}
