package contacts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppController app = new AppController();
        System.out.print("Enter action (add, remove, edit, count, list, exit): ");
        String command = scanner.nextLine();
        while (!command.equals("exit")) {
            System.out.println(app.applyCommand(command));
            System.out.print("Enter action (add, remove, edit, count, list, exit): ");
            command = scanner.nextLine();
        }
    }
}
