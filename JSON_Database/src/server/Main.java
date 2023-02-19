package server;

import server.Controllers.DBController;
import server.Controllers.SessionController;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        DBController array = new DBController();

        try {
            SessionController session = new SessionController(array);
            session.start();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
