package com.example.zealand_electric.Controllers;

import android.widget.EditText;

import com.example.zealand_electric.UseCases;

import entities.User;


public class LoginController {
    public static User user = null;
    public User tryLogin(EditText username, EditText password){
        String loginUsername = username.getText().toString();
        String loginPassword = password.getText().toString();
        UseCases uc = new UseCases();
        User user = uc.trylogin(loginUsername, loginPassword);
        return user;
    }
}
