package sn.moustapha.schoolmanager.logic;

import sn.moustapha.schoolmanager.objects.*;
import sn.moustapha.schoolmanager.objects.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SchoolManager {
    private ArrayList<Person> persons;
    private SQLConnector dbConnector;

    public SchoolManager() throws SQLException {
        persons = new ArrayList<>();
        dbConnector = new SQLConnector();
        loadPersons();
    }

    // Admin functions

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void removePerson(Person person) {
        persons.remove(person);
    }
    public void addTeacher(Teacher teacher) {
        persons.add(teacher);
    }

    public void addCourse(Class schoolClass, Course course) {
        schoolClass.addCourse(course);
    }

    public void removeCourse(Class schoolClass, Course course) {
        schoolClass.removeCourse(course);
    }

    public void addStudent(Student student, Class schoolClass) {
        schoolClass.addStudent(student);
    }

    public void removeStudent(Student student, Class schoolClass) {
        schoolClass.removeStudent(student);
    }

    // Students functions
    public void seeGrades(Student student) {
        HashMap<Course, Integer> grades = student.getGrades();
        for (Course course: grades.keySet()) {
            String subject = course.getSubject();
            Teacher teacher = course.getTeacher();
            int grade = grades.get(course);
            System.out.println(subject + ": " + grade +" Teacher: Mr." +
                    teacher);
        }
    }

    //Teacher functions
    public void gradeStudent(Student student,Course course, int grade) {
        student.obtainsGrade(course, grade);
    }

   public void seeClass(Class schoolClass, Course course) {
        ArrayList<Student> students = schoolClass.getStudents();
        for (Student student : students) {
            System.out.println("Course: " + course);
            int grade = student.getGrade(course);
            System.out.println(student + ": " + grade);
        }
   }

   // Manager functions

    public Person findPerson(int id, String password) {
        for (Person person : persons) {
            boolean hasSameId = (person.getUserId() == id);
            boolean hasSamePassword = (person.getPassword().equals(password));
            if (hasSameId && hasSamePassword)
                return person;
        }
        return null;
    }

    public void loadPersons() throws SQLException {
        dbConnector.connectToDatabase();
        ResultSet loadedResults = dbConnector.loadAccounts();
        while (loadedResults.next()) {
            int userId = loadedResults.getInt("account_id");
            String firstName = loadedResults.getString("first_name");
            String lastName = loadedResults.getString("last_name");
            String password = loadedResults.getString("password");
            Person person = null;
            String role = loadedResults.getString("account_type");
            switch (role) {
                case "Student":
                    person = new Student(userId,firstName,lastName, password);
                    break;

                case "Teacher":
                    person = new Teacher(userId,firstName,lastName, password);
                    break;

                case "Admin":
                    person = new Admin(userId,firstName,lastName, password);
                    break;

                default:
                    continue;
            }
            persons.add(person);
        }
    }


}
