package server.Controllers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;


public class ServerSocketController extends ServerSocket {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;

    public ServerSocketController() throws IOException {
        super(PORT, 50, InetAddress.getByName(ADDRESS));
        System.out.println("Server started!");
    }


}