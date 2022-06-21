package com.example.zealand_electric.Controllers;

import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.widget.LinearLayoutCompat;

import java.util.ArrayList;

import entities.User;

public class WireDetails_Controller {
    public static User user = null;


    //------------------------------------------------------------------------------------------
    /*
    WIREDETAILS
    */
    public ArrayList outputvalue (TableLayout table ) {
        //pass View v as well and call the specific layout
        //LinearLayoutCompat ll = v.findViewById(R.id.linearLayout_ID);
        ArrayList arrayList = new ArrayList();
        try {

            for (int i = 0; i < table.getChildCount(); i++) {
                LinearLayoutCompat ll = (LinearLayoutCompat) table.getChildAt(i);
                //if (table.getChildAt(i) instanceof LinearLayoutCompat) {

                for (int j = 0; j  < ll.getChildCount(); j++) {
                    if (ll.getChildAt(j)instanceof TableRow){
                        TableRow tr = (TableRow) ll.getChildAt(j);
                        //the -1 is because there are 4 children in each row, and ew only need the first 3 (the button is the 4th)
                        int get_tr_edittext = tr.getChildCount() -1;

                        for (int k = 0; k < get_tr_edittext ; k++) {
                            EditText et = (EditText) tr.getChildAt(k);

                            arrayList.add(et.getText().toString());
                            System.out.println(et.getText().toString());
                        }
                    }
                    else{
                        System.out.println("there are no tablerows");
                    }
                }
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("finished collectng all values in layout");
        return arrayList;
    }

    //------------------------------------------------------------------------------------------

    public ArrayList setValuesInOrder(ArrayList outputList1, ArrayList outputList2, ArrayList outputList3){
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
    }



    //------------------------------------------------------------------------------------------

}
