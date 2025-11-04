import java.util.ArrayList;
import java.util.Scanner;

public class Kev {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Kev");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine().trim();

            try {
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                }

                else if (input.equalsIgnoreCase("list")) {
                    System.out.println("    ____________________________________________________________");
                    if (tasks.isEmpty()) {
                        System.out.println("     Your list is empty!");
                    } else {
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println("     " + (i + 1) + "." + tasks.get(i));
                        }
                    }
                    System.out.println("    ____________________________________________________________");
                }

                else if (input.startsWith("mark ")) {
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        tasks.get(idx).markAsDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("       " + tasks.get(idx));
                        System.out.println("    ____________________________________________________________");
                    } else {
                        throw new KevException("That task number doesn’t exist!");
                    }
                }

                else if (input.startsWith("unmark ")) {
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        tasks.get(idx).markAsNotDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     OK, I've marked this task as not done yet:");
                        System.out.println("       " + tasks.get(idx));
                        System.out.println("    ____________________________________________________________");
                    } else {
                        throw new KevException("That task number doesn’t exist!");
                    }
                }

                else if (input.startsWith("delete ")) {
                    int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        Task removed = tasks.remove(idx);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println("       " + removed);
                        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");
                    } else {
                        throw new KevException("That task number doesn’t exist!");
                    }
                }

                else if (input.startsWith("todo")) {
                    String desc = input.length() > 4 ? input.substring(4).trim() : "";
                    if (desc.isEmpty()) {
                        throw new KevException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(desc));
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks.get(tasks.size() - 1));
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                }

                else if (input.startsWith("deadline")) {
                    if (!input.contains("/by")) {
                        throw new KevException("Deadlines must include '/by' followed by a date/time.");
                    }
                    String[] parts = input.substring(8).split("/by", 2);
                    String desc = parts[0].trim();
                    String by = parts[1].trim();
                    if (desc.isEmpty() || by.isEmpty()) {
                        throw new KevException("OOPS!!! The description or /by field of a deadline cannot be empty.");
                    }
                    tasks.add(new Deadline(desc, by));
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks.get(tasks.size() - 1));
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                }

                else if (input.startsWith("event")) {
                    if (!input.contains("/from") || !input.contains("/to")) {
                        throw new KevException("Events must include '/from' and '/to' fields.");
                    }
                    String[] parts = input.substring(5).split("/from|/to");
                    String desc = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new KevException("OOPS!!! Description, /from, or /to field cannot be empty.");
                    }
                    tasks.add(new Event(desc, from, to));
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks.get(tasks.size() - 1));
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                }

                else {
                    throw new KevException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (KevException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     " + e.getMessage());
                System.out.println("    ____________________________________________________________");
            } catch (Exception e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Something went wrong: " + e.getMessage());
                System.out.println("    ____________________________________________________________");
            }
        }

        sc.close();
    }
}
