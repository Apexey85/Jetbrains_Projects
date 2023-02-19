package server.Types;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

public class Command {
    @Expose
    public String type;
    @Expose
    public JsonElement key;
    @Expose
    public JsonElement value;

    public Command() {
        type = "";
        key = null;
        value = null;
    }
}
