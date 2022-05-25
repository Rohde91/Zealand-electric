package com.example.zealand_electric;

import android.widget.EditText;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import entities.CheckList;
import entities.Customer;
import entities.User;

public class DBController {

    private static String username = "root";
    private static String password = "";
    public static java.lang.String url;
    private static Connection connection;

    public static void connectToDatabase() {
         url = "jdbc:mysql://10.0.2.2:3306/zealandelectric";
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
        connectToDatabase();

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
        closeConnection();
        return user;
    }


    public static int insertIntoCustomerTableAndReturnID(Customer customer){
        int result = -1;

        String mySQL = "INSERT INTO customer (customerName, customerAdress, fk_zipCode) VALUES ('"
                + customer.getCustomerName() + "','" + customer.getCustomerAdress() + "','" + customer.getFk_zipCode() + "')";
        try{

            Statement statement = connection.createStatement();
            statement.execute(mySQL,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                result=rs.getInt(1);
            }
            rs.close();
            statement.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public static void insertIntoUser(String fullName, String username, String password, String userRole){

        String mySQL = "INSERT INTO user (fullName, username, password, userRole) VALUES ('"
                + fullName + "','" + username + "','" + password +"','" + userRole + "')";
        try{

            Statement statement = connection.createStatement();


            statement.execute(mySQL);


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Customer customerGet(Customer customer){

        try{
            Statement statement = connection.createStatement();
        String mySQL = "SELECT * FROM customer" +
                " WHERE customerName='" + customer.getCustomerName() +
                "' AND customerAdress='" + customer.getCustomerAdress() +
                "'";

        ResultSet rs = statement.executeQuery(mySQL);

        if (rs.next()){
            customer = new Customer(rs.getInt("id"),
                    rs.getString("customerName"),
                    rs.getString("customerAdress"),
                    rs.getString("fk_zipCode"));
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;

    }

    public static void insertIntoCheckList(CheckList checkList){

        String mySQL = "INSERT INTO checklist (fk_customerId, date, caseNumber, installationLocation, installer, fk_userId, crossOhm, installationNote, checklistComplete,checklistConfirmed)" +
                "VALUES ('" + checkList.getFk_customerId() +
                "','" + checkList.getDate() +
                "','" + checkList.getCaseNumber() +
                "','" + checkList.getInstallationLocation() +
                "','" + checkList.getInstaller() +
                "','" + checkList.getFk_userId() +
                "','" + checkList.getCrossOhm() +
                "','" + checkList.getCrossOhm() +
        "','" + checkList.getChecklistComplete() + "','" + checkList.getChecklistConfirmed() + "')";

        try{

            Statement statement = connection.createStatement();

            statement.execute(mySQL);


        } catch (SQLException e){
            e.printStackTrace();
        }

    }


}
