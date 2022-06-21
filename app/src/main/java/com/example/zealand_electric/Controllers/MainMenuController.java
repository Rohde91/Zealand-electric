package com.example.zealand_electric.Controllers;

import android.widget.LinearLayout;

import com.example.zealand_electric.Fragments.MainMenuFragment;

import java.util.ArrayList;

import entities.CheckList;

public class MainMenuController {


    public static CheckList getChecklist(LinearLayout openCaseButtonField){

        MainMenuFragment f = new MainMenuFragment();

        System.out.println("getchecklist køres");

        ArrayList openCases = DBController.getOpenChecklists();


        System.out.println(" ");
        System.out.println(openCases.size());

        for (int i = 0; i < openCases.size(); i++) {
            System.out.println("for loop running "+ i);
            f.createOpenChecklistButton(openCaseButtonField);



        CheckList checklist = (CheckList) openCases.get(i);
        return checklist;
        }

        System.out.println("for loop kørt i mmc");

        return null;
    }
}
/*
            for (int i = 0; i < openCases.size(); i++) {
                // TODO: Knapper oprettes
                mainMenuFragment.createOpenChecklistButton(openCases, buttonLayout, view);
        }*/
/*
public static void createOpenChecklistButton(LinearLayout openCaseButtonField){
    final View openCaseButtons = getLayoutInflater().inflate(R.layout.main_menu_button,null,false);
    openCaseButtonField.addView(openCaseButtons);
    System.out.println("noget knap");
}
*/

