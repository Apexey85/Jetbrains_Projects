package contacts.Controllers.ContactCommand;

public class CommandRemove implements Command {

    private final ContactsCommands phoneCommand;


    public CommandRemove(ContactsCommands phoneCommand) {
        this.phoneCommand = phoneCommand;
    }

    @Override
    public String execute() {
        return phoneCommand.remove();
    }
}

