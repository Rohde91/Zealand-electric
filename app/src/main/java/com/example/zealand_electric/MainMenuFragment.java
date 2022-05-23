package com.example.zealand_electric;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import entities.User;


public class MainMenuFragment extends Fragment{
    User user = LoginFragment.user;

    //Button myButton = View.findViewById(R.id.ConfirmChecklistButton);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button createChecklist = view.findViewById(R.id.CreateChecklistButton);
        Button ConfirmChecklistButton = view.findViewById(R.id.ConfirmChecklistButton);
        Button AddWorkerButton = view.findViewById(R.id.AddWorkerButton);

        String UserRole = user.getUserRole();


        if (Objects.equals(UserRole, "Lærer")) {

            ConfirmChecklistButton.setVisibility(View.VISIBLE);
            AddWorkerButton.setVisibility(View.VISIBLE);
        }
        else {
            ConfirmChecklistButton.setVisibility(View.GONE);
            AddWorkerButton.setVisibility(View.GONE);
            System.out.println("didn't read if");
        }

        createChecklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MainMenuFragment.this)
                        .navigate(R.id.action_mainMenu_to_newCustomerFragment);
                 }
       });

        ConfirmChecklistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainMenuFragment.this)
                        .navigate(R.id.action_mainMenu_to_confirm_Checklist);
                System.out.println(user.getFullName());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //  binding = null;
    }
}