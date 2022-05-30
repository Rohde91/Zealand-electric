package com.example.zealand_electric;

import junit.framework.TestCase;

import org.junit.Test;

import entities.User;

public class LoginFragmentTest extends TestCase {

    public void testOnCreate() {
    }

    public void testOnCreateView() {
    }

    @Test
    public void testOnViewCreated() {
        User user = new User("marc572m","1234");
        DBController trylogin = new DBController();
        DBController.connectToDatabase();
        User rs = trylogin.tryUserLogin("marc572m","1234");
        assertEquals(rs,user);
        DBController.closeConnection();
    }

    public void testOnDestroyView() {
    }

    public void testSum() {
        LoginFragment lf = new LoginFragment();
        int res = lf.sum(10,5);
        assertEquals(15,res);

    }
}