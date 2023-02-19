package server.Controllers;

import com.google.gson.Gson;
import server.Enums.OperationStatus;
import server.Types.Command;
import server.Types.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SessionController extends Thread {
    private Command nextCommand;
    private final DBController array;

    public SessionController(DBController array) throws IOException {
        this.array = array;
        this.nextCommand = new Command();
    }


    @Override
    public void run() {
        try {
            ServerSocketController server = new ServerSocketController();
            while (!nextCommand.type.equals("exit")) {
                try (
                        Socket socket = server.accept();
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                ) {
                    String msg = input.readUTF();
                    if (!nextCommand.type.equals("exit")) {
                        nextCommand = new Gson().fromJson(msg, Command.class);

                        Response answer = array.Operation(nextCommand);

                        output.writeUTF(new Gson().toJson(answer));

                    } else {
                        output.writeUTF(OperationStatus.OK.name);
                    }
                } catch (IOException e) {
                    e.getMessage();
                }
            }
            server.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}

