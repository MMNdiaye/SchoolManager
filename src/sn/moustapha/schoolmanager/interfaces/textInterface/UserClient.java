package sn.moustapha.schoolmanager.interfaces.textInterface;

import sn.moustapha.schoolmanager.logic.SchoolManager;
import sn.moustapha.schoolmanager.objects.Admin;
import sn.moustapha.schoolmanager.objects.Person;
import sn.moustapha.schoolmanager.objects.Student;
import sn.moustapha.schoolmanager.objects.Teacher;

import java.sql.SQLException;
import java.util.Scanner;

public class UserClient {
    protected Person user;
    protected final SchoolManager schoolManager = new SchoolManager();
    private UserClient client;
    protected Scanner scanner;

    public UserClient() throws SQLException {
        scanner = new Scanner(System.in);
    }


    protected final void login () throws SQLException {
        System.out.println("Login interface");
        boolean loggedIn = false;
        while (true) {
            // Logging option
            System.out.println("Log in? y/n");
            String response =  scanner.next().toLowerCase();
            if (response.equals("n"))
                break;

            // User login inputs
            System.out.print("Enter id: ");
            int userId = scanner.nextInt();
            System.out.println("Enter password");
            String password = scanner.next();
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
    }

    public void start() throws SQLException {
        login();
        if (client != null)
            client.launch();
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
