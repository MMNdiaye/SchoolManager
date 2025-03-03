package sn.moustapha.schoolmanager.logic;

import sn.moustapha.schoolmanager.objects.*;
import sn.moustapha.schoolmanager.objects.Class;

import java.sql.*;

public class SQLConnector {
    private Connection con;

    public void connectToDatabase() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/school";
        String uname = "postgres";
        String password = "Boompow16+";

        con = DriverManager.getConnection(url, uname, password);
    }

    public void disconnect() throws SQLException {
        con.close();
    }

    // loading functions

    public ResultSet loadAccounts() throws SQLException {
        Statement statement  = con.createStatement();
        String query = "SELECT * FROM accounts";
        ResultSet result = statement.executeQuery(query);
        return result;
    }

    public ResultSet loadClasses() throws SQLException {
        Statement statement = con.createStatement();
        String query = "SELECT * FROM classes";
        ResultSet result = statement.executeQuery(query);
        return result;
    }

    public ResultSet loadCourses() throws SQLException {
        Statement statement = con.createStatement();
        String query = "SELECT * FROM courses";
        ResultSet result = statement.executeQuery(query);
        return result;
    }

    public ResultSet loadStudents(int classId, int courseId) throws SQLException {
        String query = "SELECT student_id, first_name, last_name, grade" +
                " FROM accounts JOIN (students JOIN grades"  +
                " ON students.student_id = grades.student_id)" +
                " ON students.student_id = accounts.account_id" +
                " WHERE class_id = " + classId + " AND course_id = " + courseId;
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        return result;
    }

    public ResultSet loadTeachers() throws SQLException {
        Statement statement = con.createStatement();
        String query = "SELECT teacher_id, first_name, last_name "
                        + "FROM teachers JOIN accounts ON "
                        + "teachers.teacher_id = accounts.account_id";
        ResultSet result = statement.executeQuery(query);
        return result;
    }

    // Inserting functions

    public void insertClass() throws SQLException {
        Statement statement = con.createStatement();
        String query = "INSERT INTO classes DEFAULT VALUES";
        statement.executeUpdate(query);
    }

    private void insertAccount(Person person, String role) throws SQLException {
        // Inserting new account
        String query = "INSERT INTO accounts(password, first_name, last_name, " +
                "account_type) VALUES (?,?,?,?);";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, person.getPassword());
        preparedStatement.setString(2, person.getFirstName());
        preparedStatement.setString(3, person.getLastName());
        preparedStatement.setString(4, role);
        preparedStatement.executeUpdate();

        // return created account id
        query = "SELECT lastval();";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        int accountId =rs.getInt(1);
        person.setUserId(accountId);

    }

    private void initializeGrades(int studentId, int classId) throws SQLException {
        ResultSet courses = getCourses(classId);
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO grades(student_id, course_id) VALUES");
        while(courses.next()) {
            query.append("(").append(studentId).append(", ")
                    .append(courses.getInt("course_id")).append(")");
            if (!courses.isLast())
                query.append(", ");
        }

        Statement statement = con.createStatement();
        statement.executeUpdate(query.toString());

    }

    private ResultSet getCourses(int classId) throws SQLException {
        Statement statement = con.createStatement();
        String query = "SELECT course_id FROM courses " +
                " WHERE class_id = " + classId;
        ResultSet result = statement.executeQuery(query);
        return result;
    }

    public void insertStudent(Student student , int classId) throws SQLException {
        insertAccount(student,"Student");

        String query = "INSERT INTO students VALUES(?,?);";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, student.getUserId());
        statement.setInt(2, classId);
        statement.executeUpdate();
        initializeGrades(student.getUserId(), classId);
        System.out.println("Student account created successfully");
    }

    public void insertTeacher(Teacher teacher) throws SQLException {
        insertAccount(teacher,"Teacher");

        String query = "INSERT INTO teachers VALUES (?);";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, teacher.getUserId());
        statement.executeUpdate();
        System.out.println("Teacher account created successfully");
    }

    public void insertAdmin(Admin admin) throws SQLException {
        insertAccount(admin,"Admin");

        String query = "INSERT INTO admins VALUES (?);";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, admin.getUserId());
        statement.executeUpdate();
        System.out.println("Admin account created successfully");
    }

    public void insertCourse(Course course) throws SQLException {
        //Inserting new course
        String query = "INSERT INTO courses(subject, teacher_id, class_id) " +
                "VALUES (?,?,?);";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, course.getSubject());
        preparedStatement.setInt(2, course.getTeacher().getUserId());
        preparedStatement.setInt(3, course.getClassroom().getClassId());
        preparedStatement.executeUpdate();

        //return created course id
        query = "SELECT Lastval();";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        int courseId = rs.getInt(1);
        course.setCourseId(courseId);
    }

    // Deleting functions

    public void deleteAccount(Person person) throws SQLException {
        int accountId = person.getUserId();

        String query = "DELETE FROM accounts WHERE account_id = ?;";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, accountId);
        statement.executeUpdate();
        System.out.println("Account deleted successfully");
    }

    public void deleteCourse(Course course) throws SQLException {
        int courseId= course.getCourseId();

        String query = "DELETE FROM courses WHERE course_id = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, courseId);
        statement.executeUpdate();
        System.out.println("Course deleted successfully");
    }

    public void deleteClass(Class classroom) throws SQLException {
        int classId = classroom.getClassId();
        String query = "DELETE FROM classes WHERE class_id = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, classId);
        statement.executeUpdate();
        System.out.println("Class deleted successfully");
    }

}
