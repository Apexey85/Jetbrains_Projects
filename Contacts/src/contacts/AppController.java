package contacts;

import contacts.Types.ContactRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppController {
    private final List<ContactRecord> contactsList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public AppController () {}

    public String applyCommand(String command) {
        return switch (command) {
            case "add" -> addCommand();
            case "remove" -> removeCommand();
            case "edit" -> editCommand();
            case "count" -> countCommand();
            case "list" -> listCommand();
            default -> "Wrong command!";
        };
    }

    private String addCommand() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();

        ContactRecord Record = new ContactRecord.Builder()
                .setName(name)
                .setSurname(surname)
                .setPhoneNumber(phoneNumberCheck(phoneNumber))
                .build();
        contactsList.add(Record);
        return "The record added.";
    }

    private String listCommand() {
        StringBuilder listBuilder = new StringBuilder();
        for (int i = 0; i < this.contactsList.size(); i++) {
            listBuilder.append(i + 1);
            listBuilder.append(". ");
            listBuilder.append(this.contactsList.get(i).getName());
            listBuilder.append(" ");
            listBuilder.append(this.contactsList.get(i).getSurname());
            listBuilder.append(", ");
            listBuilder.append(this.contactsList.get(i).getPhoneNumber());
            listBuilder.append(i + 1 != this.contactsList.size() ? "\n" : "");
        }
      return listBuilder.toString();
    }

    private String countCommand(){
        return "The Phone Book has " + this.contactsList.size() + " records.";
    }

    private String editCommand() {
        if (contactsList.size() != 0) {
            System.out.println(this.listCommand());
            System.out.print("Select a record: ");
            int i = Integer.parseInt(scanner.nextLine());
            if (i > 0 && i <= contactsList.size()){
                System.out.print("Select a field (name, surname, number): ");
                String field = scanner.nextLine();
                switch (field) {
                    case "name" -> {
                        System.out.print("Enter name: ");
                        String newName = scanner.nextLine();
                        this.contactsList.get(i - 1).setName(newName);
                        return "The record updated!";
                    }
                    case "surname" -> {
                        System.out.print("Enter surname: ");
                        String newSurname = scanner.nextLine();
                        this.contactsList.get(i - 1).setSurname(newSurname);
                        return "The record updated!";
                    }
                    case "number" -> {
                        System.out.print("Enter number: ");
                        String newPhoneNumber = scanner.nextLine();
                        this.contactsList.get(i - 1).setPhoneNumber(phoneNumberCheck(newPhoneNumber));
                        return "The record updated!";
                    }
                    default -> {
                        return "No such field!";
                    }
                }
            } else {
                return "No such record!";
            }
        } else {
            return "No records to edit!";
        }
    }

    private String removeCommand() {
        System.out.println(this.listCommand());
        if (contactsList.size() != 0) {
            System.out.print("Select a record: ");
            int i = scanner.nextInt();
            if (i > 0 && i <= contactsList.size()){
                contactsList.remove(i - 1);
            } else {
                System.out.println("No such record!");
            }
            return "The record removed!";
        } else {
            return "No records to remove!";
        }
    }

    private String phoneNumberCheck(String phoneNumber){
        Pattern numberMatcher = Pattern.compile( "[+]?(\\(?\\w+\\)?[- ]\\w{2,}|\\w+[- ]\\(?\\w{2,}\\)?|\\(?\\w*\\)?)([- ]\\w{2,})*");
        Matcher number = numberMatcher.matcher(phoneNumber);
        if (number.matches()){
            return phoneNumber;
        } else return "[no number]";

    }


}
