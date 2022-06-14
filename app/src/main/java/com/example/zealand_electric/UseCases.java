package com.example.zealand_electric;

import com.example.zealand_electric.Controllers.DBController;

import entities.User;

public class UseCases {

    static User user = null;



    public User trylogin (String loginUsername, String loginPassword){

    DBController tryLogin = new DBController();
    user = tryLogin.tryUserLogin(loginUsername, loginPassword);

    return user;
    }



}
