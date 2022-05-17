package com.example.zealand_electric;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    EditText username;
    TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Hides toolbar/actionbar
        getSupportActionBar().hide();



        //makes the login button switch scenes
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = findViewById(R.id.username);
                password = findViewById(R.id.password);

                username.getText();
                password.getEditText().getText();

                System.out.println(username.getText()+ "\r" + password.getEditText().getText());

                //switches scene from this to class
                startActivity (new Intent(LoginActivity.this,MainActivity.class ) );

            }
        });//end of loginButton

    }
}