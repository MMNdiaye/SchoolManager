package sn.moustapha.schoolmanager.logic;

import java.sql.*;
import java.util.ArrayList;

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


    public ResultSet loadAccounts() throws SQLException {
        Statement statement  = con.createStatement();
        String query = "SELECT * FROM accounts";
        ResultSet result = statement.executeQuery(query);
        return result;
    }




}
