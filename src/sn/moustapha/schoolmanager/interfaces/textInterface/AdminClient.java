package sn.moustapha.schoolmanager.interfaces.textInterface;

import java.util.Scanner;

public class AdminClient extends UserClient{

    public void start() {
        System.out.println("Hello " + user + "What do you want to do?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            StringBuilder options = new StringBuilder();
            options.append("\n 1- Create account");
            options.append("\n 2- Remove account");
            options.append("\n 3- Add course");
            options.append("\n 4- Remove course");
            options.append("\n 5- Add class");
            options.append("\n 6- Remove class");
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
