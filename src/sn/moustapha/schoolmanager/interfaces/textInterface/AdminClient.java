package sn.moustapha.schoolmanager.interfaces.textInterface;

import sn.moustapha.schoolmanager.objects.*;
import sn.moustapha.schoolmanager.objects.Class;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminClient extends UserClient{

    public AdminClient() {

    }

    public void launch()  {
        System.out.println("Hello " + user + "! What do you want to do?");
        StringBuilder options = new StringBuilder();
        options.append("\n 1- Create account");
        options.append("\n 2- Remove account");
        options.append("\n 3- Add course");
        options.append("\n 4- Remove course");
        options.append("\n 5- Add class");
        options.append("\n 6- Remove class");
        options.append("\n 0- quit");

        while (true) {
            System.out.println(options);
            int option = sc.nextInt();
            try {

                switch (option) {
                    case 1:
                        getAccountInfos();
                        break;

                    case 2:
                        removeAccount();
                        break;

                    case 3:
                        getCourseInfos();
                        break;

                    case 4:
                        removeCourse();
                        break;

                    case 5 :
                        schoolManager.addClass();
                        break;

                    case 6:
                        removeClass();
                        break;

                    case 0:
                        System.out.println("See you next time");
                        user = null;
                        break;

                    default:
                        break;
                }
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            if (user == null)
                break;

        }
    }

    // Account creation methods

    private void getAccountInfos() throws SQLException {
        System.out.println("First name: ");
        String firstName = sc.next();
        System.out.println("Last name: ");
        String lastName = sc.next();
        System.out.println("Password: ");
        String password = sc.next();
        System.out.println("Role: ");
        String role = sc.next();

        if (role.equals("Student"))
            getStudentInfos(firstName, lastName, password);
        else if (role.equals("Teacher"))
            getTeacherInfos(firstName, lastName, password);
        else
            getAdminInfos(firstName, lastName, password);

    }

    private void getStudentInfos(String firstName, String lastName, String password) throws SQLException{
        // get class ids and create one if needed
        System.out.print("Select a class id: ");
        boolean hasClasses = schoolManager.showClasses();
        if (!hasClasses) {
            System.out.println("No classes would you want to create one? y/n");
            String response = sc.next();
            if (response.toLowerCase().equals("y"))
                schoolManager.addClass();
            else
                return;
        }

        int classId = sc.nextInt();
        Student student = new Student(firstName, lastName, password);
        schoolManager.addStudent(student, classId);

    }

    private void getTeacherInfos(String firstName, String lastName, String password) throws SQLException {
        Teacher teacher = new Teacher(firstName, lastName, password);
        schoolManager.addTeacher(teacher);
    }

    private void getAdminInfos(String firstName, String lastName, String password) throws SQLException {
        Admin admin = new Admin(firstName, lastName, password);
        schoolManager.addAdmin(admin);
    }


    // Account removal method
    private void removeAccount() throws SQLException {
        System.out.println("Id of account to remove: ");
        int accountId = sc.nextInt();
        Person person = schoolManager.findPerson(accountId);
        schoolManager.removePerson(person);
    }

    //Course creation method
    private void getCourseInfos() throws SQLException {
        System.out.print("\nEnter course subject: ");
        String subject = sc.next();

        System.out.println("Enter teacher id: ");
        boolean foundTeachers = schoolManager.showTeachers();
        if(!foundTeachers) {
            System.out.println("No teachers in the school");
            return;
        }
        int teacherId = sc.nextInt();

        System.out.println("Enter class id: ");
        boolean foundClasses = schoolManager.showClasses();
        if(!foundClasses) {
            System.out.println("No classes in the school");
            return;
        }
        int classId = sc.nextInt();

        Teacher teacher = (Teacher) schoolManager.findPerson(teacherId);
        Class classroom = new Class(classId);
        Course course = new Course(subject, teacher, classroom);
        schoolManager.addCourse(course);


    }

    // Course removal method
    private void removeCourse() throws SQLException {
        System.out.println("Id of course to remove: ");
        int courseId = sc.nextInt();
        Course course = schoolManager.findCourse(courseId);
        schoolManager.removeCourse(course);
    }

    // Class removal method
    private void removeClass() throws SQLException {
        System.out.println("Id of class to remove: ");
        int classId = sc.nextInt();
        Class classroom = new Class(classId);
        schoolManager.removeClass(classroom);
    }





}
