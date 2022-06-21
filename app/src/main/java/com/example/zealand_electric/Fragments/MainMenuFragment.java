package com.example.zealand_electric.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.zealand_electric.Controllers.DBController;
import com.example.zealand_electric.Controllers.MainMenuController;
import com.example.zealand_electric.R;

import java.sql.SQLException;
import java.util.ArrayList;
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

        LinearLayout openCaseButtonField = view.findViewById(R.id.openCaseButtonField);

        String UserRole = user.getUserRole();



            System.out.println("mmf Try run");


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try  {
                        MainMenuController.getChecklist(openCaseButtonField);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();


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


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //  binding = null;
    }

    CheckList checkList = new CheckList(1, 1, 1,0,"bob");
    // int id, int fk_customerId, int fk_userId, Integer checklistComplete, String customerAdress




    public void createOpenChecklistButton (LinearLayout openCaseButtonField){
        final View openCaseButton = getLayoutInflater().inflate(R.layout.main_menu_button,null,false);
        System.out.println("button creation called");
        Button openChecklist = openCaseButton.findViewById(R.id.openCaseButton);


        openChecklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO sæt navigation
                /*NavHostFragment.findNavController(MainMenuFragment.this)
                        .navigate(R.id.);*/

                System.out.println("Knap klikkes");
            }
        });


        openCaseButtonField.addView(openCaseButton);
    }

}