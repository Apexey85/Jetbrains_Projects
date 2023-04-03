package contacts.Controllers.ContactCommand;

public class Controller {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public String executeCommand() {
        return command.execute();
    }

}
