package com.example.zealand_electric;

import com.example.zealand_electric.Controllers.DBController;
import com.example.zealand_electric.Fragments.NewCustomerFragment;

import java.util.ArrayList;

import entities.User;

public class UseCases {

    static User user = null;



    public User trylogin (String loginUsername, String loginPassword){

    DBController tryLogin = new DBController();
    user = tryLogin.tryUserLogin(loginUsername, loginPassword);

    return user;
    }

    //------------------------------------------------------------------------------------------
    /*
    WIREDETAILS
    */

    public void insertTableData(ArrayList listOfAllValues){

        DBController.connectToDatabase();
        int numberOfValuesInAllRows = 9;
        int numberOfRows = listOfAllValues.size()/numberOfValuesInAllRows;

        for (int i = 0; i < numberOfRows; i++) {

            DBController.insertIntoCircuitDetails(NewCustomerFragment.checkList.getId(),listOfAllValues);
            for (int j = 0; j < 9; j++) {
                listOfAllValues.remove(0);
            }
        }
        DBController.closeConnection();
    }



}
