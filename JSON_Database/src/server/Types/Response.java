package server.Types;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.Expose;

public class Response {
    @Expose
    public String response;
    @Expose
    public JsonElement value;
    @Expose
    public JsonObject reason;

    public Response(String response, String reason) {
        this.reason = new JsonObject().getAsJsonObject(reason);
        this.response = response;
    }

    public Response(String response, JsonElement value, Boolean isGet) {
        if (value.isJsonPrimitive()) {
            this.value = new JsonObject().getAsJsonPrimitive(value.getAsString());
        } else {
            this.value = value.getAsJsonObject();
        }
        this.response = response;
    }

    public Response(String response, String value, Boolean isGet) {
        this.value = new JsonPrimitive(value);
        this.response = response;
    }
    public Response(String response) {
        this.response = response;
    }
}
