import java.util.Scanner;
public class Dor {
    public static void main(String[] args) {
        String line;
        Task[] storage = new Task[100];
        Task currTask;
        int counter = 0;
        int whichToChange = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Dor");
        System.out.println("What can I do for you?");
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.contains("unmark")) {
                line = line.substring(7);
                whichToChange = Integer.parseInt(line) - 1;
                storage[whichToChange].markNotDone();
                System.out.println("Ok, I've marked this Task as not done yet:");
                System.out.println("[ ] " + storage[whichToChange].getName());
                line = in.nextLine();
            } else if (line.contains("mark")) {
                line = line.substring(5);
                whichToChange = Integer.parseInt(line) - 1;
                storage[whichToChange].markDone();
                System.out.println("Nice! I've marked this Task as done:");
                System.out.println("[X] " + storage[whichToChange].getName());
                line = in.nextLine();
            } else {
                switch (line) {
                case "bye":
                    break;
                case "list":
                    for (int i = 0; i < counter; i++) {
                        System.out.println((i + 1) + ".[" + storage[i].getDoneStatus() + "] " + storage[i].getName());
                    }
                    line = in.nextLine();
                    break;
                default:
                    currTask = new Task(line, false);
                    System.out.println("added: " + line);
                    storage[counter] = currTask;
                    counter++;
                    line = in.nextLine();
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
