package com.example.zealand_electric;

import com.example.zealand_electric.Controllers.DBController;

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
    /*public ArrayList setValuesInOrder(ArrayList outputList1, ArrayList outputList2, ArrayList outputList3){
        ArrayList allValues = new ArrayList();

        System.out.println(" ");
        System.out.println("outputlist1 size = " + outputList1.size());


        final int numberOfRowsInEachTable = outputList1.size()/3;
        System.out.println("number of rows in each table "+numberOfRowsInEachTable);


        // This loops takes all the values from list 1,2,3 and adds the first 3 values from each list to the arraylist allValues
        //and does this as many times as there there are rows
        //giving us an arraylist that we easily can insert into our Database
        for (int k = 0; k < numberOfRowsInEachTable; k++) {
            System.out.println("number of times first loop was called =" + (k+1));

            for (int i = 0; i < 3; i++) {
                allValues.add(outputList1.get(0));
                outputList1.remove(0);
            }

            for (int i = 0; i < 3; i++) {
                allValues.add(outputList2.get(0));
                outputList2.remove(0);
            }

            for (int i = 0; i < 3; i++) {
                allValues.add(outputList3.get(0));
                outputList3.remove(0);
            }
        }
        return allValues;
    }*/


}
