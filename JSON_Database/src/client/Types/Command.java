package client.Types;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;


import java.util.Arrays;

public class Command {
    @Expose
    public String type;
    @Expose
    public JsonElement key;
    @Expose
    public JsonElement value;

    public Command(String[] args) {
        JsonObject commandObject = new JsonObject();
        try {
            for (int i = 0; i < args.length; i = i + 2) {
                if (!args[i].equals("-v")) {
                    commandObject.addProperty(args[i], args[i + 1]);
                } else{
                    String value = String.join(" ", Arrays.copyOfRange(args, 5, args.length));
                    commandObject.addProperty("-v", value);
                    break;
                }
            }

            this.type = commandObject.get("-t").getAsString();
            if (!type.equals("exit")) {
                this.key = commandObject.get("-k");
            }
            if (this.type.equals("set")) {
                this.value = commandObject.get("-v");
            }
        } catch (Exception e) {
            System.out.println("Error in command creation: -> " + e.getMessage());
        }
    }
}
