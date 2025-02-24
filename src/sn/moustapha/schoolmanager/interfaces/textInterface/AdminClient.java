package sn.moustapha.schoolmanager.interfaces.textInterface;

import sn.moustapha.schoolmanager.objects.Student;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminClient extends UserClient{

    public AdminClient() throws SQLException {
    }

    public void launch() throws SQLException {
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

            switch (option) {
                case 1:


            }
            if (option == 0) {
                System.out.println("See you next time");
                user = null;
                login();
                break;
            }

        }
    }

    private void createAccount() {
        System.out.print("Enter first name: ");
        String firstName = scanner.next();
        System.out.println("Enter last name: ");
        String lastName = scanner.next();
        System.out.println("Enter account password: ");
        String password = scanner.next();
        System.out.println("Select role: ");
        String role = scanner.next();

        if (role.equals("student")) {
            Student student = new Student(firstName, lastName, password);
            schoolManager.addStudent(student);
        }
    }



}
