package sn.moustapha.schoolmanager;

import sn.moustapha.schoolmanager.interfaces.textInterface.UserClient;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            UserClient ui = new UserClient();
            ui.start();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
