package sn.moustapha.schoolmanager.logic;

import sn.moustapha.schoolmanager.objects.*;
import sn.moustapha.schoolmanager.objects.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SchoolManager {
    private ArrayList<Person> persons;
    private ArrayList<Course> courses;
    private SQLConnector dbConnector;
    private boolean connectedToDb;

    public SchoolManager() {
        persons = new ArrayList<>();
        courses = new ArrayList<>();
        dbConnector = new SQLConnector();
        try {
            dbConnector.connectToDatabase();
            loadPersons();
            loadCourses();
            connectedToDb = true;
        } catch (SQLException e) {
            System.out.println("Error loading database");
            connectedToDb = false;
        }

    }

    // Admin functions

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void removePerson(Person person) throws SQLException {
        persons.remove(person);
        dbConnector.deleteAccount(person);

    }

    public void addClass() throws SQLException {
        dbConnector.insertClass();
    }

    public void removeClass(Class classroom) throws  SQLException {
        dbConnector.deleteClass(classroom);
    }

    public void addCourse(Course course) throws SQLException {
        courses.add(course);
        dbConnector.insertCourse(course);
    }

    public void removeCourse(Course course) throws SQLException {
        courses.remove(course);
        dbConnector.deleteCourse(course);
    }

    public void addStudent(Student student, int classId) throws SQLException {
        addPerson(student);
        dbConnector.insertStudent(student, classId);
    }

    public void addTeacher(Teacher teacher) throws SQLException {
        addPerson(teacher);
        dbConnector.insertTeacher(teacher);
    }

    public void addAdmin(Admin admin) throws SQLException {
        addPerson(admin);
        dbConnector.insertAdmin(admin);
    }


    // Students functions
     public void seeGrades(Person student) throws SQLException {
        ResultSet resultSet = dbConnector.loadGrades(student.getUserId());
        while (resultSet.next()) {
            String subject = resultSet.getString("subject");
            int grade = resultSet.getInt("grade");
            System.out.println("Subject: " + subject);
            System.out.println("Grade: " + grade);
        }
    }

    //Teacher functions
    public void gradeStudent(Person student, Course course, int grade) throws SQLException {
        int studentId = student.getUserId();
        int courseId = course.getCourseId();
        dbConnector.gradeStudent(studentId, courseId, grade);
    }

    public void showClasses(Person teacher) {
        for (Course course : courses) {
            if (course.getTeacher().getUserId() == teacher.getUserId())
                System.out.println("Class id: " + course.getClassroom().getClassId() +
                        " Course Id: " + course.getCourseId() +
                        " Subject: " + course.getSubject());
        }

    }

    public void showStudents(int classId, int courseId) throws SQLException {
        ResultSet resultSet = dbConnector.loadStudents(classId, courseId);
        while (resultSet.next()) {
            int studentId = resultSet.getInt("student_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int grade = resultSet.getInt("grade");
            System.out.println("id: " + studentId + " " +
                                firstName + " " + lastName + " " +
                                "grade: " + grade);
        }


    }

   // Manager functions

    public boolean hasConnectedToDb() {
        return connectedToDb;
    }

    public boolean showClasses() throws SQLException {
        ResultSet rs = dbConnector.loadClasses();
        boolean hasNextEntry = rs.next();
        if (!hasNextEntry)  // empty
            return false;

        while (hasNextEntry) {
            System.out.print(rs.getInt("class_id") + ", ");
            hasNextEntry = rs.next();
        }

        return true;
    }

    public boolean showTeachers() throws SQLException {
        ResultSet resultSet = dbConnector.loadTeachers();
        boolean hasTeacher = resultSet.next();
        if (!hasTeacher)
            return false;

        while (hasTeacher) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int teacherId = resultSet.getInt("teacher_id");
            System.out.println("id: " + teacherId + " " + firstName +
                    " " + lastName);
            hasTeacher = resultSet.next();
        }
        return true;
    }

    public Person findPerson(int id, String password) {
        for (Person person : persons) {
            boolean hasSameId = (person.getUserId() == id);
            boolean hasSamePassword = (person.getPassword().equals(password));
            if (hasSameId && hasSamePassword)
                return person;
        }
        return null;
    }

    public Person findPerson(int id) {
        for (Person person : persons) {
            boolean hasSameId = (person.getUserId() == id);
            if (hasSameId)
                return person;
        }
        return null;
    }

    public void loadPersons() throws SQLException {
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

    public void loadCourses() throws SQLException {
        ResultSet resultSet = dbConnector.loadCourses();
        while (resultSet.next()) {
            int courseId = resultSet.getInt("course_id");
            String subject = resultSet.getString("subject");
            Teacher teacher = (Teacher) findPerson(resultSet.getInt("teacher_id"));
            Class classroom = new Class(resultSet.getInt("class_id"));
            Course course = new Course(courseId, subject, teacher, classroom);
            courses.add(course);
        }
    }

    public Course findCourse(int id) {
        for (Course course : courses) {
            boolean hasSameId = (course.getCourseId() == id);
            if (hasSameId)
                return course;
        }
        return null;
    }



}
