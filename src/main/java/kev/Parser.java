package kev;

import kev.command.*;
import kev.exception.KevException;

/**
 * responsible for parsing user input into executable commands.
 */
public class Parser {

    /**
     * parses the user input and returns the corresponding command.
     * throws an exception if the input is invalid or incomplete.
     *
     * @param input The raw user input string.
     * @return The corresponding Command object.
     * @throws KevException If the input is invalid or unrecognized.
     */
    public static Command parse(String input) throws KevException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                return parseMarkCommand(words);

            case "unmark":
                return parseUnmarkCommand(words);

            case "delete":
                return parseDeleteCommand(words);

            case "todo":
            case "deadline":
            case "event":
                return parseAddCommand(input, words);

            case "on":
                return parseOnCommand(words);

            case "snooze":
                return parseSnoozeCommand(words);

            case "find":
                return parseFindCommand(words);

            case "remind":
                return new RemindCommand();

            default:
                throw new KevException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command parseMarkCommand(String[] words) throws KevException {
        if (words.length < 2) {
            throw new KevException("☹ OOPS!!! You must provide the task number to mark.");
        }
        try {
            int index = Integer.parseInt(words[1]) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new KevException("☹ OOPS!!! The task number must be a valid integer.");
        }
    }

    private static Command parseUnmarkCommand(String[] words) throws KevException {
        if (words.length < 2) {
            throw new KevException("☹ OOPS!!! You must provide the task number to unmark.");
        }
        try {
            int index = Integer.parseInt(words[1]) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new KevException("☹ OOPS!!! The task number must be a valid integer.");
        }
    }

    // ================= Helper methods for parsing specific commands =================
    private static Command parseDeleteCommand(String[] words) throws KevException {
        if (words.length < 2) {
            throw new KevException("☹ OOPS!!! You must provide the task number to delete.");
        }
        try {
            int index = Integer.parseInt(words[1]) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new KevException("☹ OOPS!!! The task number must be a valid integer.");
        }
    }

    private static Command parseAddCommand(String input, String[] words) throws KevException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new KevException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        return new AddCommand(input);
    }

    private static Command parseOnCommand(String[] words) throws KevException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new KevException("Please provide a date: on YYYY-MM-DD");
        }
        return new OnCommand(words[1].trim());
    }

    private static Command parseSnoozeCommand(String[] words) throws KevException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new KevException("Please provide task number and new date: snooze <num> YYYY-MM-DD");
        }
        String[] args = words[1].split(" ", 2);
        if (args.length < 2) throw new KevException("Provide both task number and new date.");
        int snoozeIndex;
        try {
            snoozeIndex = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            throw new KevException("Task number must be an integer.");
        }
        return new SnoozeCommand(snoozeIndex, args[1].trim());
    }

    private static Command parseFindCommand(String[] words) throws KevException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new KevException("☹ OOPS!!! You must provide a keyword to find.");
        }
        return new FindCommand(words[1].trim());
    }
}


