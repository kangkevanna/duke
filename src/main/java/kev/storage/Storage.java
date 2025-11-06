package kev.storage;

import kev.task.*;
import kev.exception.KevException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws KevException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];

                switch (type) {
                    case "T":
                        Task todo = new Todo(desc);
                        if (isDone) todo.markAsDone();
                        tasks.add(todo);
                        break;
                    case "D":
                        if (parts.length < 4) throw new KevException("Invalid file format for deadline");
                        Task deadline = new Deadline(desc, parts[3]);
                        if (isDone) deadline.markAsDone();
                        tasks.add(deadline);
                        break;
                    case "E":
                        if (parts.length < 4) throw new KevException("Invalid file format for event");
                        Task event = new Event(desc, parts[3]);
                        if (isDone) event.markAsDone();
                        tasks.add(event);
                        break;
                    default:
                        throw new KevException("Unknown task type in file");
                }
            }
        } catch (IOException e) {
            throw new KevException("Failed to read file: " + e.getMessage());
        }

        return tasks;
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
        }
    }
}
