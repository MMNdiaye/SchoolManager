package sn.moustapha.schoolmanager.interfaces.textInterface;

import java.util.Scanner;

public class StudentClient extends UserClient {

    public void start() {
        System.out.println("Hello " + user + "What do you want to do?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            StringBuilder options = new StringBuilder();
            options.append("\n 1- See grades");
            options.append("\n 0- quit");
            System.out.println(options);

            int option = sc.nextInt();
            if (option == 0) {
                System.out.println("See you next time");
                user = null;
                login();
                break;
            }

        }
    }
}
