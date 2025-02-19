package sn.moustapha.schoolmanager.interfaces.textInterface;

import sn.moustapha.schoolmanager.logic.SchoolManager;
import sn.moustapha.schoolmanager.objects.Admin;
import sn.moustapha.schoolmanager.objects.Person;
import sn.moustapha.schoolmanager.objects.Student;
import sn.moustapha.schoolmanager.objects.Teacher;

import java.util.Scanner;

public class UserClient {
    protected Person user;
    protected final SchoolManager schoolManager = new SchoolManager();
    private UserClient client;

    public UserClient() {
        login();
    }


    protected final void login() {
        System.out.println("Login interface");
        Scanner sc = new Scanner(System.in);
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
                sc.close();
                break;
            }

            System.out.println("Account not found.");

        }
    }

    public void start() {
        if (client != null)
            client.start();
    }

    private void createUserInterface() {
        if (user instanceof Student)
            client = new StudentClient();
        else if (user instanceof Teacher)
            client = new TeacherClient();
        else if (user instanceof Admin)
            client = new AdminClient();

    }
}
