package com.example.zealand_electric;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.User;

public class DBController {

    private static Connection connection;

    public static void connectToDatabase() {
        java.lang.String url = "jdbc:mysql://10.0.2.2:3306/zealandelectric";
        try {
            connection = DriverManager.getConnection(url,"root","" );

        } catch (SQLException e) {
            e.printStackTrace();

            }

    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //---------------------------------------------------------------------------------------------------------------

    /**
     * FUNCTIONS
     */

    public String userRole(String email) {
        String result = "";
        try {
            PreparedStatement userRole = connection.
                    prepareStatement("SELECT user FROM  WHERE Email = '" + email + "'");
            ResultSet rs = userRole.executeQuery();
            while (rs.next()){
                result = rs.getString("userRole");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public User TryUserLogin (String email, String password) {
        ResultSet buildUserResultset = null;
        String sql;
        String userRole = userRole(email);

        try {
            //Prøv at logge ind:
            Statement statement = connection.createStatement();
            sql = "SELECT * FROM user" +
                    " WHERE Email='" + email +
                    "' AND Password='" + password +
                    "'";

            ResultSet checkLoginRS = statement.executeQuery(sql);

            if (checkLoginRS.next()){
                sql = "SELECT * FROM " + userRole +
                        " WHERE Email='" + email +
                        "'";
                PreparedStatement preparedStmt = connection.prepareStatement(sql);
                ResultSet rs = preparedStmt.executeQuery();

                if (rs.next()){
                    String result = rs.getString(userRole);
                    System.out.println("Velkommen " + result + ".");
                }
                else{
                    System.out.println("Du er ikke tildelt nogen stilling.");
                }
            }
            else{
                System.out.println("Forkert Email eller Password.");
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
