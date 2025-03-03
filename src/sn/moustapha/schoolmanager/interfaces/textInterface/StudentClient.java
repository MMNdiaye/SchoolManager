package sn.moustapha.schoolmanager.interfaces.textInterface;

import java.sql.SQLException;
import java.util.Scanner;

public class StudentClient extends UserClient {

    public StudentClient() {
    }

    public void launch() {
        System.out.println("Hello " + user + "! What do you want to do?");
        StringBuilder options = new StringBuilder();
        options.append("\n 1- See grades");
        options.append("\n 0- quit");
        while (true) {
            System.out.println(options);
            int option = sc.nextInt();

        try {

            if (option == 0) {
                System.out.println("See you next time");
                user = null;
                break;
            }

            if (option == 1)
                schoolManager.seeGrades(user);

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        }
    }
}
