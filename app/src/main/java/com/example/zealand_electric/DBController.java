package com.example.zealand_electric;

import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Customer;
import entities.User;

public class DBController {

    private static String username = "root";
    private static String password = "";

    private static Connection connection;

    public static void connectToDatabase() {
        java.lang.String url = "jdbc:mysql://10.0.2.2:3306/zealandelectric";
        try {
            connection = DriverManager.getConnection(url, username, password );

        } catch (SQLException e) {
            e.printStackTrace();

            }
    }
    private static volatile DBController instance;

    //singleton implementation
    public static DBController getInstance () {
        DBController result = instance;
        if (result == null) {
            synchronized (DBController.class) {
                result = instance;
                if (result == null) {
                    instance = result = new DBController();
                }
            }
        }
        return result;
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

    public String userRole(String username) {
        String result = "";
        try {
            PreparedStatement userRole = connection.
                    prepareStatement("SELECT FROM user WHERE username = '" + username + "'");
            ResultSet rs = userRole.executeQuery();
            while (rs.next()){
                result = rs.getString("userRole");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public User TryUserLogin (String username, String password) {
        String sql;
        //String userRole = userRole(username);

        User user = null;

        try {
            //Prøv at logge ind:
            Statement statement = connection.createStatement();
            sql = "SELECT * FROM user" +
                    " WHERE username='" + username +
                    "' AND password='" + password +
                    "'";
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()){
                user = new User(rs.getInt("id"),
                        rs.getString("fullName"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("userRole"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void insertIntoCustomerTable(String customerName, String customerAdress, String customerZipcode){

        String mySQL = "INSERT INTO customer (customerName, customerAdress, fk_zipCode) VALUES ('"
                + customerName + "','" +customerAdress + "','" + customerZipcode + "')";
        try{

            Statement statement = connection.createStatement();
            statement.execute(mySQL);


        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void insertInto(){}



}
