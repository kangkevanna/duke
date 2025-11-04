package kev.storage;

import kev.task.*;
import kev.exception.KevException;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private static final String DATA_FOLDER = "./data";
    private static final String FILE_PATH = DATA_FOLDER + "/duke.txt";

    public Storage() {
        // Ensure folder exists
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        // Ensure file exists
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating data file: " + e.getMessage());
        }
    }

    /**
     * Load tasks from the file into an ArrayList<Task>.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue; // skip empty lines
                String[] parts = line.split(" \\| ");

                if (parts.length < 3) continue; // skip malformed lines

                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                Task task = null;
                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        if (parts.length < 4) continue;
                        String by = parts[3].trim();
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        if (parts.length < 4) continue;
                        String at = parts[3].trim();
                        String[] times = at.split("-");
                        String from = times[0].trim();
                        String to = times.length > 1 ? times[1].trim() : "";
                        task = new Event(description, from, to);
                        break;
                    default:
                        continue; // unknown type
                }

                if (isDone) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Save tasks to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
