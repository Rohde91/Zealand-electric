package com.example.zealand_electric.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zealand_electric.Controllers.DBController;
import com.example.zealand_electric.R;

import entities.User;

public class LoginFragment extends Fragment {

    public static User user = null;
    Button loginButton;
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

        loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> new Thread(() -> {



            EditText username = view.findViewById(R.id.username);
            EditText password = view.findViewById(R.id.password);

            String loginUsername = username.getText().toString();
            String loginPassword = password.getText().toString();

            DBController tryLogin = new DBController();
            user = tryLogin.tryUserLogin(loginUsername, loginPassword);


            if (user != null){
                getActivity().runOnUiThread(new Runnable() {
                @Override
                    public void run() {
                    Toast.makeText(getActivity(), "Logger ind", Toast.LENGTH_SHORT).show();

                        NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.action_loginFragment_to_mainMenu);
                    }
                });
            }
            else {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Forkert brugernavn eller password", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start());
    }
    //usertest
    int sum(int a, int b){
        return a+b;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //  binding = null;
    }
}