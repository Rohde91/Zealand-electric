package com.example.zealand_electric.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.CheckList;
import entities.Customer;
import entities.User;

public class DBController {

    private static String username = "root";
    private static String password = "";
    public static java.lang.String url;
    private static Connection connection;
    private CheckList CheckList;

    //method

    public static Connection connectToDatabase() {
         url = "jdbc:mysql://10.0.2.2:3306/zealandelectric";
        try {
            connection = DriverManager.getConnection(url, username, password );
            return connection;

        } catch (SQLException e) {
            e.printStackTrace();

            }
        return null;
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

    public CheckList BuildCheckListObject (ResultSet rs) throws SQLException{

        CheckList = null;

        CheckList checklist = new CheckList();
        while (rs.next()){
            checklist.setId(rs.getInt("id"));
            checklist.setFk_customerId(rs.getInt("fk_customerId"));
            checklist.setFk_userId(rs.getInt("fk_userId"));
            checklist.setChecklistComplete(rs.getInt("checklistComplete"));
        }

        return CheckList;
    }

public static ArrayList<CheckList> openChecklist(){

    ArrayList<CheckList> openCases = new ArrayList<CheckList>();

    try {
        String sql = "Select * From checklist WHERE fk_userid = 'user' && checklistComplete = 0";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){

            CheckList openCaseList = new CheckList();

                    openCaseList.setId(rs.getInt("id"));
                    openCaseList.setFk_customerId(rs.getInt("fk_customerId"));
                    openCaseList.setFk_userId(rs.getInt("fk_userId"));
                    openCaseList.setChecklistComplete(rs.getInt("checklistComplete"));

            openCases.add(openCaseList);

            for (int i = 0; i < openCases.size(); i++) {
                
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return openCases;
}


    public User tryUserLogin(String username, String password) {
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
    //---------------------------------------------------------------------------------------------------------------


    public void openCheckilsts(){

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
    //---------------------------------------------------------------------------------------------------------------

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

    public static int insertIntoCheckListAndReturnId(CheckList checkList){
        int result = -1;

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

    public static void insertIntoCheckListRow (int checklistId, int fk_questionId, int fk_valueId,String documentationImage, String notes, int fk_userid,int category_position){

        String mySQL = "INSERT INTO checklistrow (fk_checklistId, fk_questionId, fk_valueId, documentationImage, notes, fk_userId, category_position) " +
                "VALUES ('" + checklistId +
                "','" + fk_questionId +
                "','"  + fk_valueId +
                "','" + documentationImage +
                "','"+ notes +
                "','"  + fk_userid +"','"+ category_position + "')" ;

        try{

            Statement statement = connection.createStatement();

            statement.execute(mySQL);


        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void insertIntoCircuitDetails(int fk_checklist, String groupName, String ob,
                                                String characteristics, String crossSection, String maxOb, String zS, String rA, String ohm, String isolation){
        String mySQL = "INSERT INTO checklistrow (fk_checklistId, groupName, ob, characteristics, crossSection, maxOb, zS, rA, ohm, isolation) " +
                "VALUES ('" + fk_checklist + "','" + groupName +
                "','"  + ob + "','" + characteristics + "','"+
                crossSection + "','"  + maxOb + "','" + zS + "','" + rA + "','" + ohm + "','" + isolation + "')" ;

        try{

            Statement statement = connection.createStatement();

            statement.execute(mySQL);


        } catch (SQLException e){
            e.printStackTrace();
        }


    }

    public static void insertIntoPowerDrop(int fk_checklistId, String groupName, String uPercent, String readPoint){
        String mySQL = "INSERT INTO checklistrow (fk_checklistId, groupName, uPercent, readPoint) " +
                "VALUES ('" + fk_checklistId + "','" + groupName + "','" + uPercent + "','" + readPoint + "')" ;

        try{

            Statement statement = connection.createStatement();

            statement.execute(mySQL);


        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void insertIntoRCDTest(int fk_checklistId, String rcdRead, String sinusAoneEightZero, String sinusAcZeroFive, String pulseZero, String pulseOneEightZero, String testButton){
        String mySQL = "INSERT INTO checklistrow (fk_checklistId, rcdRead, sinusAoneEightZero, sinusAcZeroFive, sinusAcZero, pulseZero, pulseOneEightZero, testButton) " +
                "VALUES ('" + fk_checklistId + "','" + rcdRead + "','" + sinusAoneEightZero + "','" + sinusAcZeroFive + "','" + pulseZero + "','" + pulseOneEightZero + "','" + testButton + "')" ;

        try{

            Statement statement = connection.createStatement();

            statement.execute(mySQL);


        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void insertIntoShortCircuit(int fk_checklistId, String groupName, String ikA, String readPoint){
        String mySQL = "INSERT INTO checklistrow (fk_checklistId, groupName, ikA, readPoint) " +
                "VALUES ('" + fk_checklistId + "','" + groupName + "','" + ikA + "','" + readPoint + "')" ;

        try{

            Statement statement = connection.createStatement();

            statement.execute(mySQL);


        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}
