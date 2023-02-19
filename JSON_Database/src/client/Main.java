package client;


import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import client.Types.Command;

public class Main {

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 23456;

    public static void main(String[] args) throws IOException {
        String msg = "-in testSet.json";
        args = msg.split(" ");
        //String msg;
        if (args[0].equals("-in")) {
            String folder = "/Users/apexey/Desktop/Java/Stepik/Spring/JSON Database/JSON Database/task/src/client/data/";
            msg = new String(Files.readAllBytes(Paths.get(folder + args[1])));
        } else {
            Command command = new Command(args);
            msg = new Gson().toJson(command);
        }

        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Client started!");
            output.writeUTF(msg);
            System.out.println("Sent: " + msg);
            String receivedMsg = input.readUTF();
            System.out.println("Received: " + receivedMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
