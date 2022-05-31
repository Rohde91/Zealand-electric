package com.example.zealand_electric;

import com.example.zealand_electric.Controllers.DBController;

import junit.framework.TestCase;

public class DBControllerTest extends TestCase {

    public void testTryUserLogin() {
        DBController trylogin = new DBController();
        DBController.connectToDatabase();
        assertEquals((null), trylogin.tryUserLogin("marc572m","1234"));
        DBController.closeConnection();
    }

    public void testConnectToDatabase() {
        DBController conn = new DBController();
        conn.connectToDatabase();
        assertEquals("jdbc:mysql://10.0.2.2:3306/zealandelectric",conn.url);
    }
}