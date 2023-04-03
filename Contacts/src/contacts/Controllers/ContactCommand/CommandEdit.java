package contacts.Controllers.ContactCommand;

public class CommandEdit implements Command {

    private final ContactsCommands phoneCommand;

    public CommandEdit(ContactsCommands phoneCommand) {
        this.phoneCommand = phoneCommand;

    }

    @Override
    public String execute() {
        return phoneCommand.edit();
    }
}

