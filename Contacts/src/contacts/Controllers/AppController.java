package contacts.Controllers;

import contacts.Controllers.ContactCommand.*;
import contacts.Types.Contact;
import contacts.Types.OrganizationContact;
import contacts.Types.PersonContact;

import java.util.ArrayList;
import java.util.List;


public class AppController {
    private final List<Contact> contactsList = new ArrayList<>();

    public AppController () {
        this.contactsList.add(0, new PersonContact.Builder()
                .setName("Alex")
                .setSurname("Grin")
                .setGender("M")
                .setBirthday("1985-03-23")
                .setPhoneNumber("+0 (890) 123-34-12")
                .build());
        this.contactsList.add(0, new OrganizationContact.Builder()
                .setName("Pizza Hut")
                .setAddress("Wall st, 1")
                .setPhoneNumber("+0 (890) 123-34-12")
                .build());
    }

    public String applyCommand(String command) {

        Controller controller = new Controller();
        ContactsCommands contactsCommands = new ContactsCommands(contactsList);

        Command addCommand = new CommandAdd(contactsCommands);
        Command infoCommand = new CommandInfo(contactsCommands);
        Command removeCommand = new CommandRemove(contactsCommands);
        Command editCommand = new CommandEdit(contactsCommands);
        Command countCommand = new CommandCount(contactsCommands);

        switch (command) {
            case "add" -> controller.setCommand(addCommand);
            case "remove" -> controller.setCommand(removeCommand);
            case "edit" -> controller.setCommand(editCommand);
            case "count" -> controller.setCommand(countCommand);
            case "info" -> controller.setCommand(infoCommand);
            default -> {
                return "Wrong command!";
            }
        }
        return controller.executeCommand();
    }
}
