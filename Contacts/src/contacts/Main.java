package contacts;

import contacts.Types.ContactRecord;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppController app = new AppController();
        ContactRecord currentRecord = new ContactRecord();

        System.out.println("Enter the name of the person:");
        currentRecord.setName(scanner.nextLine());
        System.out.println("Enter the surname of the person:");
        currentRecord.setSurname(scanner.nextLine());
        System.out.println("Enter the number:");
        currentRecord.setPhoneNumber(scanner.nextLine());

        if (app.AddRecord(currentRecord)) {
            System.out.println();
            System.out.println("A record created!");
            System.out.println("A Phone Book with a single record created!");
        }




    }
}
