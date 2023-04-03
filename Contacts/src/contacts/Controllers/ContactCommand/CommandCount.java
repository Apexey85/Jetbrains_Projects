package contacts.Controllers.ContactCommand;

public class CommandCount implements Command {

    private final ContactsCommands phoneCommand;

    public CommandCount(ContactsCommands phoneCommand) {
        this.phoneCommand = phoneCommand;
    }

    @Override
    public String execute() {
        return phoneCommand.count();
    }
}
