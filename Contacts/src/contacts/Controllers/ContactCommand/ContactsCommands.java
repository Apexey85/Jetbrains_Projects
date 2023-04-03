package contacts.Controllers.ContactCommand;

import contacts.Types.Contact;
import contacts.Types.OrganizationContact;
import contacts.Types.PersonContact;

import java.io.IOError;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactsCommands {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Contact> contactsList;

    public ContactsCommands(List<Contact> contactRecords) {
        this.contactsList = contactRecords;
    }

    protected String add() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();
        if (type.equals("organization")) {
            return addOrganization();
        } else if (type.equals("person")) {
            return addPerson();
        } else return "Wrong command!";
    }
    private String addPerson () {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the birth date: ");
        String birthday = scanner.nextLine();
        if (birthday.equals("")) System.out.println("Bad birth date!");
        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        if (gender.equals("")) System.out.println("Bad gender!");
        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();

        Contact Record = new PersonContact.Builder()
                .setName(name)
                .setSurname(surname)
                .setBirthday(birthday)
                .setGender(gender)
                .setPhoneNumber(phoneNumberCheck(phoneNumber))
                .build();
        this.contactsList.add(Record);
        return "The record added.\n";
    }

    private String addOrganization() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();
        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();

        Contact record = new OrganizationContact.Builder()
                .setName(name)
                .setAddress(address)
                .setPhoneNumber(phoneNumberCheck(phoneNumber))
                .build();
        this.contactsList.add(record);
        return "The record added.\n";
    }


    private String list() {
        StringBuilder listBuilder = new StringBuilder();
        for (int i = 0; i < this.contactsList.size(); i++) {
            listBuilder.append(i + 1);
            listBuilder.append(". ");
            if (isPerson(this.contactsList.get(i))) {
                PersonContact personContact = (PersonContact) this.contactsList.get(i);
                listBuilder.append(personContact.getName());
                listBuilder.append(" ");
                listBuilder.append(personContact.getSurname());
                listBuilder.append(i + 1 != this.contactsList.size() ? "\n" : "");
            } else {
                OrganizationContact orgContact = (OrganizationContact) this.contactsList.get(i);
                listBuilder.append(orgContact.getName());
                listBuilder.append(i + 1 != this.contactsList.size() ? "\n" : "");
            }
        }
        return listBuilder.toString();
    }

    protected String info() {
        System.out.println(list());
        int index = -1;
        if (!list().equals("")) {
            System.out.print("Enter index to show info: ");
            try {
                index = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage() + ". Wrong input format");
            }
        }
        StringBuilder infoBuilder = new StringBuilder();

        if (index >= 0 && index <= contactsList.size() - 1 && isPerson(contactsList.get(index))){
            PersonContact personContact = (PersonContact) contactsList.get(index);
            infoBuilder.append("Name: ")
                    .append(personContact.getName())
                    .append("\n");
            infoBuilder.append("Surname: ")
                    .append(personContact.getSurname())
                    .append("\n");
            infoBuilder.append("Birth date: ")
                    .append(personContact.getBirthday())
                    .append("\n");
            infoBuilder.append("Gender: ")
                    .append(personContact.getGender())
                    .append("\n");
            infoBuilder.append("Number: ")
                    .append(personContact.getPhoneNumber())
                    .append("\n");
            infoBuilder.append("Time created: ")
                    .append(personContact.getCreatedTime())
                    .append("\n");
            infoBuilder.append("Time last edit: ")
                    .append(personContact.getEditTime())
                    .append("\n");
        } else if (index >= 0 && index <= contactsList.size() - 1 && !isPerson(contactsList.get(index))){
            OrganizationContact orgContact = (OrganizationContact) contactsList.get(index);
            infoBuilder.append("Organization name: ")
                    .append(orgContact.getName())
                    .append("\n");
            infoBuilder.append("Address: ")
                    .append(orgContact.getAddress())
                    .append("\n");
            infoBuilder.append("Number: ")
                    .append(orgContact.getPhoneNumber())
                    .append("\n");
            infoBuilder.append("Time created: ")
                    .append(orgContact.getCreatedTime())
                    .append("\n");
            infoBuilder.append("Time last edit: ")
                    .append(orgContact.getEditTime())
                    .append("\n");
        } else infoBuilder.append("No such record!");

        return infoBuilder.toString();
    }

    protected String count(){
        return "The Phone Book has " + this.contactsList.size() + " records.";
    }
    private String editPerson(int i) {
        PersonContact personContact = (PersonContact) contactsList.get(i - 1);
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = scanner.nextLine();
        switch (field) {
            case "name" -> {
                System.out.print("Enter name: ");
                String newName = scanner.nextLine();
                personContact.setName(newName);
                return "The record updated!\n";
            }
            case "surname" -> {
                System.out.print("Enter surname: ");
                String newSurname = scanner.nextLine();
                personContact.setSurname(newSurname);
                return "The record updated!\n";
            }
            case "number" -> {
                System.out.print("Enter number: ");
                String newPhoneNumber = scanner.nextLine();
                personContact.setPhoneNumber(phoneNumberCheck(newPhoneNumber));
                return "The record updated!\n";
            }
            case "birth" -> {
                System.out.print("Enter birth date: ");
                String newBirthday = scanner.nextLine();
                personContact.setBirthday(LocalDate.parse(newBirthday));
                return "The record updated!\n";
            }
            case "gender" -> {
                System.out.print("Enter gender: ");
                String newGender = scanner.nextLine();
                personContact.setGender(newGender);
                return "The record updated!\n";
            }
            default -> {
                return "No such field!\n";
            }
        }
    }
    private String editOrganization(int i) {
        OrganizationContact orgContact = (OrganizationContact) contactsList.get(i - 1);
        System.out.print("Select a field (address, number): ");
        String field = scanner.nextLine();
        switch (field) {
            case "address" -> {
                System.out.print("Enter address: ");
                String newAddress = scanner.nextLine();
                orgContact.setAddress(newAddress);
                return "The record updated!\n";
            }
            case "number" -> {
                System.out.print("Enter number: ");
                String newPhoneNumber = scanner.nextLine();
                orgContact.setPhoneNumber(phoneNumberCheck(newPhoneNumber));
                return "The record updated!\n";
            }
            default -> {
                return "No such field!\n";
            }
        }
    }
    protected String edit() {
        if (contactsList.size() != 0) {
            System.out.println(this.list());
            System.out.print("Select a record: ");
            int i = Integer.parseInt(scanner.nextLine());
            if (i > 0 && i <= contactsList.size() && isPerson(contactsList.get(i - 1))) {
                return editPerson(i);
            } else if (i > 0 && i <= contactsList.size() && !isPerson(contactsList.get(i - 1))) {
                return editOrganization(i);
            } else {
                return "No such record!\n";
            }
        } else {
            return "No records to edit!\n";
        }
    }

    protected String remove() {
        System.out.println(this.list());
        if (contactsList.size() != 0) {
            System.out.print("Select a record: ");
            int i = scanner.nextInt();
            if (i > 0 && i <= contactsList.size()){
                contactsList.remove(i - 1);
            } else {
                System.out.print("No such record!");
            }
            return "The record removed!";
        } else {
            return "No records to remove!";
        }
    }

    private boolean isPerson(Contact record) {
        return record instanceof PersonContact;
    }
    private String phoneNumberCheck(String phoneNumber){
        Pattern numberMatcher = Pattern.compile( "\\+?(\\(?\\w+\\)?[- ]\\w{2,}|\\w+[- ]\\(?\\w{2,}\\)?|\\(?\\w*\\)?)([- ]\\w{2,})*");
        Matcher number = numberMatcher.matcher(phoneNumber);
        if (number.matches()){
            return phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            return "[no number]";
        }
    }
}
