package server.Enums;

public enum OperationStatus {
    OK("OK"),
    ERROR("ERROR"),
    WRONG_COMMAND("WRONG COMMAND");

    public final String name;

    OperationStatus(String name) {
        this.name = name;
    }


}
