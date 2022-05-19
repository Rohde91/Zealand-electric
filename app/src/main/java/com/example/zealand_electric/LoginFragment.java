package com.example.zealand_electric;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Statement;

import entities.User;


public class LoginFragment extends Fragment {

    String username;
    String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



/*
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);

        String usernameCheck = String.valueOf(username.getText());
        String passwordCheck = String.valueOf(password.getText());


*/
        Button loginButton = view.findViewById(R.id.loginButton);
       /* loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_mainMenu);

            }
        });

        */
        loginButton.setOnClickListener(v -> new Thread(() -> {

            System.out.println("something");

            DBController.connectToDatabase();

            EditText username = view.findViewById(R.id.username);
            TextInputEditText password = view.findViewById(R.id.password);

            String loginUsername = username.getText().toString();
            String loginPassword = password.getText().toString();

            DBController tryLogin = new DBController();
            User user = tryLogin.TryUserLogin(loginUsername, loginPassword);

            System.out.println("Knap klikkes");
            DBController.closeConnection();



            if (user != null){
                if (user.getUserRole().equals("Lærer")){

                }
                else if (user.getUserRole().equals("Elev")){
                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.action_loginFragment_to_mainMenu);
                }
            }
            else {
                System.out.println("ingen bruger fundet");
            }


        }
        ).start());


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //  binding = null;
    }
}