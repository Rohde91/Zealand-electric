package com.example.zealand_electric.Fragments;

import android.widget.EditText;

import entities.User;

public class UIcontroller {
    public static User user = null;

    public User tryLogin(EditText username, EditText password){
        String loginUsername = username.getText().toString();
        String loginPassword = password.getText().toString();
        UseCases uc = new UseCases();
        user = uc.trylogin(loginUsername,loginPassword);
        return user;
    }

}
