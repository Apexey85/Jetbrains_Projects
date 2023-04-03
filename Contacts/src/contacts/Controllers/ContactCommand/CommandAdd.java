package contacts.Controllers.ContactCommand;

public class CommandAdd implements Command {

    private final ContactsCommands phoneCommand;

    public CommandAdd(ContactsCommands phoneCommand) {
        this.phoneCommand = phoneCommand;
    }

    @Override
    public String execute() {
        return phoneCommand.add();
    }
}
