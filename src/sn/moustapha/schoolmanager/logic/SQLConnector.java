package sn.moustapha.schoolmanager.logic;

import sn.moustapha.schoolmanager.objects.Admin;
import sn.moustapha.schoolmanager.objects.Person;
import sn.moustapha.schoolmanager.objects.Student;
import sn.moustapha.schoolmanager.objects.Teacher;

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

    public void insertStudent(Student student , int classId) throws SQLException {
        insertAccount(student,"Student");

        String query = "INSERT INTO students VALUES(?,?);";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, student.getUserId());
        statement.setInt(2, classId);
        statement.executeUpdate();
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

    // Deleting functions

    public void deleteAccount(Person person) throws SQLException {
        int accountId = person.getUserId();

        String query = "DELETE FROM accounts WHERE account_id = ?;";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, accountId);
        statement.executeUpdate();
        System.out.println("Account deleted successfully");
    }

}
