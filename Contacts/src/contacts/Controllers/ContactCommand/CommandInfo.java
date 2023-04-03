package contacts.Controllers.ContactCommand;

public class CommandInfo implements Command {

    private final ContactsCommands phoneCommand;

    public CommandInfo(ContactsCommands phoneCommand) {
        this.phoneCommand = phoneCommand;
    }

    @Override
    public String execute() {
        return phoneCommand.info();
    }
}
