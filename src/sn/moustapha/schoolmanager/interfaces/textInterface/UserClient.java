package sn.moustapha.schoolmanager.interfaces.textInterface;

import sn.moustapha.schoolmanager.logic.SchoolManager;
import sn.moustapha.schoolmanager.objects.Admin;
import sn.moustapha.schoolmanager.objects.Person;
import sn.moustapha.schoolmanager.objects.Student;
import sn.moustapha.schoolmanager.objects.Teacher;

import java.sql.SQLException;
import java.util.Scanner;

public class UserClient {
    protected static Person user;
    protected final SchoolManager schoolManager = new SchoolManager();
    private UserClient client;
    protected Scanner sc;

    public UserClient() throws SQLException {
        sc = new Scanner(System.in);
    }


    protected final boolean login () throws SQLException {
        System.out.println("Login interface");
        boolean loggedIn = false;
        while (true) {
            // Logging option
            System.out.println("Log in? y/n");
            String response =  sc.next().toLowerCase();
            if (response.equals("n"))
                break;

            // User login inputs
            System.out.print("Enter id: ");
            int userId = sc.nextInt();
            System.out.println("Enter password");
            String password = sc.next();
            Person person = schoolManager.findPerson(userId, password);
            if (person != null)
                loggedIn = true;

            // Login verification

            if (loggedIn) {
                user = person;
                createUserInterface();
                break;
            }

            System.out.println("Account not found.");

        }
        return loggedIn;
    }

    public void start() throws SQLException {
        while (true) {
            boolean isLoggedIn = login();
            if (isLoggedIn)
                client.launch();
            else {
                System.out.println("Thanks.");
                break;
            }

        }

    }

    public void launch() throws SQLException {

    }
    private void createUserInterface() throws SQLException {
        if (user instanceof Student)
            client = new StudentClient();
        else if (user instanceof Teacher)
            client = new TeacherClient();
        else if (user instanceof Admin)
            client = new AdminClient();

    }
}
