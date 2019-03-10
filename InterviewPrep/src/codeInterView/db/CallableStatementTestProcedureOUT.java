package codeInterView.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/** Copyright (c), AnkitMittal JavaMadeSoEasy.com */
public class CallableStatementTestProcedureOUT {
    public static void main(String... arg) {
        Connection con = null;
        CallableStatement callableStmt = null;
        try {
            // registering Oracle driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // getting connection
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ankit", "Oracle123");
            System.out.println("Connection established successfully!");

            callableStmt = con.prepareCall("{call MYPROC_EMPLOYEE_SELECT_OUT(?,?,?,?)}");
            Statement stmt = con.createStatement();
            // IN parameter -
            // 1) set methods are used for setting IN parameter values of Stored procedure
            callableStmt.setInt(1, 11);

            // OUT parameter -
            // 1) OUT parameters must be registered in java before executing the stored
            // procedure,
            callableStmt.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStmt.registerOutParameter(3, java.sql.Types.NUMERIC);
            callableStmt.registerOutParameter(4, java.sql.Types.DATE);

            // OUT parameter -
            // 2) Execute database stored procedure,
            callableStmt.executeUpdate();

            // OUT parameter -
            // 3) Then retrieve values of OUT parameters using using get methods.
            System.out.println("name = " + callableStmt.getString(2));
            System.out.println("salary = " + callableStmt.getInt(3));
            System.out.println("creationDate = " + callableStmt.getDate(4));// returns java.Sql.Date

            System.out.println("Stored procedure executed successfully, " + "data has been fetched from Employee table");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStmt != null)
                    callableStmt.close(); // close CallableStatement
                if (con != null)
                    con.close(); // close connection
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}