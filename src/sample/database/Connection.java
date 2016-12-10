package sample.database;

import sample.Tools;

import java.sql.*;
import java.util.List;

import static sample.Tools.createString;

/**
 * Created by andrew_yashin on 12/2/16.
 */
public class Connection {
    public static final String URL = "jdbc:mysql://localhost:3306/ipk";
    public static final String DRIVER = "com.mysql.jdbc.Driver";

    public static final String USERNAME = "root";
    public static final String PASSWORD = "1";

    java.sql.Connection mConnection;
    Statement mStatement;

    public Connection() throws Exception {
            Class.forName(DRIVER);
            mConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            mStatement = mConnection.createStatement();
    }

    public Statement getStatement() {
        return mStatement;
    }

    public void setmStatement(Statement mStatement) {
        this.mStatement = mStatement;
    }

    public ResultSet executeDataFromTable(String table) throws SQLException{
        return mStatement.executeQuery("SELECT * FROM " + table);
    }

    public void executeUpdate(String tableName, String tableRow, String id, String newString, String idString)
            throws SQLException
    {
        mStatement.executeUpdate("UPDATE " + tableName + " SET " + tableRow + " = " + newString +
                " WHERE " + id + "="+idString);
    }

    public void executeInsert(String tableName, List<String> id, List<String> values) throws SQLException{
        String idString = createString(id);
        String valuesString = createString(values);

        mStatement.executeUpdate("INSERT INTO " + tableName + " " + idString + " VALUE " + valuesString);
    }

    public void executeDelete(String tableName, String id, String value) throws SQLException{
        mStatement.executeUpdate("DELETE FROM " + tableName + " WHERE " + id + "=" + value);
    }

    public java.sql.Connection getConnection() {
        return mConnection;
    }
}


