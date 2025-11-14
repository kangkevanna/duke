package kev;

import kev.command.*;
import kev.exception.KevException;

public class Parser {

    public static Command parse(String input) throws KevException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                if (words.length < 2) {
                    throw new KevException("☹ OOPS!!! You must provide the task number to mark.");
                }
                try {
                    int index = Integer.parseInt(words[1]) - 1;
                    return new MarkCommand(index);
                } catch (NumberFormatException e) {
                    throw new KevException("☹ OOPS!!! The task number must be a valid integer.");
                }

            case "unmark":
                if (words.length < 2) {
                    throw new KevException("☹ OOPS!!! You must provide the task number to unmark.");
                }
                try {
                    int index = Integer.parseInt(words[1]) - 1;
                    return new UnmarkCommand(index);
                } catch (NumberFormatException e) {
                    throw new KevException("☹ OOPS!!! The task number must be a valid integer.");
                }

            case "delete":
                if (words.length < 2) {
                    throw new KevException("☹ OOPS!!! You must provide the task number to delete.");
                }
                try {
                    int index = Integer.parseInt(words[1]) - 1;
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    throw new KevException("☹ OOPS!!! The task number must be a valid integer.");
                }

            case "todo":
            case "deadline":
            case "event":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new KevException("☹ OOPS!!! The description of a task cannot be empty.");
                }
                return new AddCommand(input);

            case "on":
                if (words.length < 2 || words[1].trim().isEmpty())
                    throw new KevException("Please provide a date: on YYYY-MM-DD");
                return new OnCommand(words[1].trim());

            default:
                throw new KevException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

