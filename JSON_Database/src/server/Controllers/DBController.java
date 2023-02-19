package server.Controllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import server.Enums.OperationStatus;
import server.Types.Command;
import server.Types.Response;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class DBController {
    private JsonObject DB = new JsonObject();
    private final String DB_ADDRESS = "/Users/apexey/Desktop/Java/Stepik/Spring/JSON Database/JSON Database/task/src/server/data/";
    private final File DB_FILE = new File(DB_ADDRESS + "db.json");
    private final Lock readLock;
    private final Lock writeLock;


    public DBController() {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
        writeDB();

    }

    public void writeDB() {
        try (FileWriter fileWriter = new FileWriter(this.DB_FILE, false))
        {
         writeLock.lock();
         fileWriter.write(new Gson().toJson(DB));
         writeLock.unlock();
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.getMessage();
        }

    }

    public void readDB() {
        try {
            readLock.lock();
            try (Reader reader = Files.newBufferedReader(Paths.get(DB_ADDRESS + "db.json"))) {
                this.DB = new Gson().fromJson(reader, JsonObject.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            readLock.unlock();

        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    public Response SetArray(JsonArray keyArray, JsonElement valueObject) {

        ArrayList<JsonObject> dbList = new ArrayList<>();
        JsonObject CurrentObject;
        dbList.add(this.DB);
        CurrentObject = this.DB.getAsJsonObject(keyArray.get(0).getAsString());
        dbList.add(CurrentObject);
        for (int i = 1; i < keyArray.size() - 1; i++) {
            CurrentObject = CurrentObject.
                    getAsJsonObject(keyArray.get(i)
                            .getAsString());
            dbList.add(CurrentObject);
        }

        dbList.get(dbList.size() - 1)
                .remove(keyArray.get(dbList.size() - 1).getAsString());
        dbList.get(dbList.size() - 1)
                .add(keyArray.get(dbList.size() - 1).getAsString(), valueObject);
        writeDB();
        return new Response(OperationStatus.OK.name);
    }
    public Response Set(JsonElement key, JsonElement value) {
        JsonElement valueObject;
        if (value.isJsonPrimitive()) {
            valueObject = value.getAsJsonPrimitive();
        } else {
            valueObject = value.getAsJsonObject();
        }
        // change existing value
        if (key.isJsonArray()) {
            JsonArray keyArray = key.getAsJsonArray();
            return SetArray(keyArray, valueObject);

        // insert new value
        } else {
            try {
                if(value.isJsonPrimitive()){
                    this.DB.add(key.getAsString(), value.getAsJsonPrimitive());
                } else {
                    this.DB.add(key.getAsString(), value.getAsJsonObject());
                }

            } catch (NullPointerException e) {
                e.getMessage();
            }
            writeDB();
            return new Response(OperationStatus.OK.name);
        }
    }

    public Response Get(JsonElement key) {
        readDB();
        if (key.isJsonPrimitive()) {
            JsonElement keyElement = key.getAsJsonPrimitive();
            if (this.DB.has(keyElement.getAsString())) {
                return new Response(OperationStatus.OK.name, this.DB.get(key.getAsString()).getAsString(), true);
            } else {
                return new Response(OperationStatus.ERROR.name, "No such key");
            }

        } else if (key.isJsonArray()) {
            JsonArray keyArray = key.getAsJsonArray();
            JsonObject CurrentObject = this.DB.getAsJsonObject(keyArray.get(0).getAsString());
            JsonElement value = null;

            if(keyArray.size() > 1) {
                for (int i = 0; i < keyArray.size() - 1; i++) {

                    if (i == keyArray.size() - 2) {
                        value = CurrentObject.get(keyArray.get(i + 1).getAsString());

                    } else {
                        CurrentObject = CurrentObject.
                                getAsJsonObject(keyArray.get(i)
                                        .getAsString());
                    }
                }
            } else {
                value = CurrentObject;
            }
            if (value != null) {
                if (value.isJsonObject()) {
                    return new Response(OperationStatus.OK.name, value, true);
                } else {
                    return new Response(OperationStatus.OK.name, value.getAsString(), true);
                }
            } else {
                return null;
            }
        }

        if (this.DB.has(key.getAsString())) {
            return new Response(OperationStatus.OK.name, this.DB.get(key.getAsString()), true);
        } else {
            return new Response(OperationStatus.ERROR.name, "No such key");
        }
    }

    public Response Delete(JsonElement key) {

        if (key.isJsonArray()) {
            JsonArray keyArray = key.getAsJsonArray();
            return DeleteArray(keyArray);
        } else {
            this.DB.remove(key.getAsString());
        }
        writeDB();
        return new Response(OperationStatus.OK.name);
    }

    private Response DeleteArray(JsonArray keyArray) {
        JsonObject CurrentObject;
        ArrayList<JsonObject> dbList = new ArrayList<>();

        dbList.add(this.DB);
        CurrentObject = this.DB.getAsJsonObject(keyArray.get(0).getAsString());
        dbList.add(CurrentObject);
        for (int i = 1; i < keyArray.size() - 1; i++) {
            CurrentObject = CurrentObject.
                    getAsJsonObject(keyArray.get(i)
                            .getAsString());
            dbList.add(CurrentObject);
        }

        dbList.get(dbList.size() - 1)
                .remove(keyArray.get(dbList.size() - 1).getAsString());
        writeDB();
        return new Response(OperationStatus.OK.name);

    }

    public Response Operation(Command nextCommand) {
        switch (nextCommand.type) {
            case "set" -> {
                //вставить проверку на Object или массив
                return Set(nextCommand.key, nextCommand.value);
            }
            case "get" -> {
                return Get(nextCommand.key);
            }
            case "delete" -> {
                return Delete(nextCommand.key);
            }
            case "exit" -> {
                return new Response(OperationStatus.OK.name);
            }
        }
        return new Response(OperationStatus.WRONG_COMMAND.name);
    }
}
