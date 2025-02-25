package sn.moustapha.schoolmanager.logic;

import sn.moustapha.schoolmanager.objects.Person;
import sn.moustapha.schoolmanager.objects.Student;

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

    // inserting functions

    public void insertClass() throws SQLException {
        Statement statement = con.createStatement();
        String query = "INSERT INTO classes DEFAULT VALUES";
        statement.executeUpdate(query);
    }

    public int insertAccount(Person person, String role) throws SQLException {
        // Inserting new account
        String query = "INSERT INTO accounts(password, first_name, last_name, " +
                "account type) VALUES (?,?,?,?) RETURNING account_id;";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, person.getPassword());
        preparedStatement.setString(2, person.getFirstName());
        preparedStatement.setString(3, person.getLastName());
        preparedStatement.setString(4, role);
        return preparedStatement.executeUpdate(); // return created account id
    }

    public void insertStudent(Student student, int classId) throws SQLException {
        int studentId = insertAccount(student, "student");
        String query = "INSERT INTO students VALUES(?,?);";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, studentId);
        statement.setInt(2, classId);
        statement.executeUpdate();
    }


}
