package com.example.zealand_electric.Controllers;

import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.zealand_electric.UseCases;

import java.util.ArrayList;

import entities.User;

public class WireDetails_Controller {
    public static User user = null;


    //------------------------------------------------------------------------------------------
    /*
    WIREDETAILS
    */

    public ArrayList collectData(TableLayout table ) {
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
                            //System.out.println(et.getText().toString());
                        }
                    }
                    else{
                        //System.out.println("there are no tablerows");
                    }
                }
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("finished collectng all values in layout");
        return arrayList;
    }

    //------------------------------------------------------------------------------------------

    public void setValuesInOrder(ArrayList outputList1, ArrayList outputList2, ArrayList outputList3){
        ArrayList allValuesInOrder = new ArrayList();

        final int numberOfRowsInEachTable = outputList1.size()/3;
         /*
         This loops takes all the values from list 1,2,3 and adds the first 3 values from each list to the arraylist allValuesInOrder
         and does this as many times as there there are rows
         giving us an arraylist that we easily can insert into our Database
         */
        for (int k = 0; k < numberOfRowsInEachTable; k++) {

            for (int i = 0; i < 3; i++) {
                allValuesInOrder.add(outputList1.get(0));
                outputList1.remove(0);
            }

            for (int i = 0; i < 3; i++) {
                allValuesInOrder.add(outputList2.get(0));
                outputList2.remove(0);
            }

            for (int i = 0; i < 3; i++) {
                allValuesInOrder.add(outputList3.get(0));
                outputList3.remove(0);
            }
        }

        UseCases ui = new UseCases();
        ui.insertTableData(allValuesInOrder);

    }

}
