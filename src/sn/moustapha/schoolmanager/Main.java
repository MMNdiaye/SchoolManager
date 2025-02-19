package sn.moustapha.schoolmanager;

import sn.moustapha.schoolmanager.interfaces.textInterface.UserClient;

public class Main {

    public static void main(String[] args) {
        UserClient ui = new UserClient();
        ui.start();
    }
}
