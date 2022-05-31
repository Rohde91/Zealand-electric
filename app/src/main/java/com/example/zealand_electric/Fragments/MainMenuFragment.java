package com.example.zealand_electric.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zealand_electric.Controllers.DBController;
import com.example.zealand_electric.R;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.Objects;

import entities.CheckList;
import entities.User;


public class MainMenuFragment extends Fragment{
    public User user = LoginFragment.user;

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
        Button addWorkerButton = view.findViewById(R.id.AddWorkerButton);
        Button openChecklist = view.findViewById(R.id.openCaseButton);

        String UserRole = user.getUserRole();



        if (Objects.equals(UserRole, "Lærer")) {

            ConfirmChecklistButton.setVisibility(View.VISIBLE);
            addWorkerButton.setVisibility(View.VISIBLE);
        }
        else {
            ConfirmChecklistButton.setVisibility(View.GONE);
            addWorkerButton.setVisibility(View.GONE);
        }

        addWorkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MainMenuFragment.this)
                        .navigate(R.id.action_mainMenu_to_addUserFragment);
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

        createChecklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MainMenuFragment.this)
                        .navigate(R.id.action_mainMenu_to_newCustomerFragment);
                //newCustomerFragment leads to checklist creation
                 }
       });

        openChecklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBController.connectToDatabase();
                DBController.openChecklist();

                DBController.closeConnection();

                //TODO sæt navigation
                /*NavHostFragment.findNavController(MainMenuFragment.this)
                        .navigate(R.id.);*/
                //DBController.openChecklist();
                System.out.println("Knap klikkes");

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //  binding = null;
    }

    CheckList checkList = new CheckList(1, 1, 1,0);
    // int id, int fk_customerId, int fk_userId, Integer checklistComplete

    public static void getOpenCaseList() throws ClassNotFoundException, SQLException{



        DBController.closeConnection();
    }
}