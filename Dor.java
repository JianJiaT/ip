import java.util.Scanner;
public class Dor {
    public static void main(String[] args) {
        String line;
        String[] storage = new String[100];
        int counter = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Dor");
        System.out.println("What can I do for you?");
        line = in.nextLine();
        while (!line.equals("bye")) {
            switch (line) {
            case "bye":
                break;
            case "list":
                for (int i = 0; i < counter; i++) {
                    System.out.println((i+1) + ". " + storage[i]);
                }
                line = in.nextLine();
                break;
            default:
                System.out.println("added: " + line);
                storage[counter] = line;
                counter++;
                line = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
