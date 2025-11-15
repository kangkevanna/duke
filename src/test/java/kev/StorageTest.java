package kev.storage;

import kev.task.*;
import kev.exception.KevException;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    private static final String TEST_PATH = "data/test-storage.txt";

    @BeforeEach
    public void cleanFile() {
        File file = new File(TEST_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void saveTasks_and_loadTasks_returnsEquivalentData() throws Exception {
        Storage storage = new Storage(TEST_PATH);

        // Original tasks
        TaskList original = new TaskList();
        original.addTask(new Todo("Read book"));
        original.addTask(new Deadline("Submit report", "2025-12-01"));
        original.addTask(new Event("Meeting", "2025-11-03"));

        // Save
        storage.saveTasks(original.getTasks());

        // Load
        List<Task> loaded = storage.loadTasks();

        assertEquals(3, loaded.size());

        // Compare each task
        assertEquals(original.getTask(0).toString(), loaded.get(0).toString());
        assertEquals(original.getTask(1).toString(), loaded.get(1).toString());
        assertEquals(original.getTask(2).toString(), loaded.get(2).toString());
    }

    @Test
    public void loadTasks_fileDoesNotExist_returnsEmptyList() throws Exception {
        Storage storage = new Storage("data/nonexistent-file.txt");

        List<Task> tasks = storage.loadTasks();

        assertTrue(tasks.isEmpty());
    }
}

