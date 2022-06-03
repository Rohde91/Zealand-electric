package com.example.zealand_electric.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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
        loginButton.setOnClickListener((View v) -> new Thread(() -> {


            EditText username = view.findViewById(R.id.username);
            EditText password = view.findViewById(R.id.password);

            UIcontroller obj = new UIcontroller();
            user = obj.tryLogin(username,password);


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

    int sum(int a, int b){
        return a+b;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //  binding = null;
    }
}