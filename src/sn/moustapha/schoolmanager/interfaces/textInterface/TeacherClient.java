package sn.moustapha.schoolmanager.interfaces.textInterface;

import sn.moustapha.schoolmanager.objects.Course;

import java.sql.SQLException;
import java.util.Scanner;

public class TeacherClient extends UserClient{

    public TeacherClient() {
    }

    public void launch() {
        System.out.println("Hello " + user + "! What do you want to do?");
        Scanner sc = new Scanner(System.in);
        StringBuilder options = new StringBuilder();
        options.append("\n 1- Consult students");
        options.append("\n 2- Grade student");
        options.append("\n 0- quit");
        while (true) {
            System.out.println(options);
            int option = sc.nextInt();
            try {
                switch (option) {
                    case 1:
                        showStudents();
                        break;

                    case 2:
                        gradeStudent();
                        break;

                    case 0:
                        user = null;
                        System.out.println("See you next time");
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

    public void showStudents() throws SQLException{
        schoolManager.showClasses(user);
        System.out.println("Select class: ");
        int classId = sc.nextInt();
        System.out.println("Select course: ");
        int courseId = sc.nextInt();
        schoolManager.showStudents(classId, courseId);
    }

    public void gradeStudent() throws SQLException {
        System.out.println("Select course id: ");
        int courseId = sc.nextInt();
        System.out.println("Select student id: ");
        int studentId  = sc.nextInt();
        System.out.println("Grade (0 - 20): ");
        int grade = sc.nextInt();
        // get student if is taught by teacher
        schoolManager.gradeStudent(schoolManager.findPerson(studentId),
                schoolManager.findCourse(courseId), grade);

    }
}
