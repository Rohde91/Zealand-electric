package com.example.zealand_electric.TabLayout;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.zealand_electric.Controllers.WireDetails_Controller;
import com.example.zealand_electric.CreationOfPDF;
import com.example.zealand_electric.Fragments.NewCustomerFragment;
import com.example.zealand_electric.R;

import java.util.ArrayList;

import entities.CheckList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WireDetails# newInstance} factory method to
 * create an instance of this fragment.
 */
public class WireDetails extends Fragment {
    public static CheckList checkList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_wire_details, container,false);

        TextView Crosscut_Value = v.findViewById(R.id.Crosscut_Value);
        Crosscut_Value.setText(Html.fromHtml("mm<sub>2</sub>"));

        TextView OB_Value = v.findViewById(R.id.OB_Value);
        OB_Value.setText(Html.fromHtml("OB (I<sub>n</sub>)"));

        TextView MaxOB_Value = v.findViewById(R.id.MaxOB_Value);
        MaxOB_Value.setText(Html.fromHtml("Maks.OB (I<sub>n</sub>)"));

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        TableLayout table1 = v.findViewById(R.id.Kredsdetalje_Tabel1_value);
        TableLayout table2 = v.findViewById(R.id.Kredsdetalje_Tabel2_value);
        TableLayout table3 = v.findViewById(R.id.Kredsdetalje_Tabel3_value);

        //buttons
        //------------------------------------------------------------------------------------------
        Button backButton = v.findViewById(R.id.tabelBackButton);
        backButton.setOnClickListener(view -> new Thread(() -> {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NavHostFragment.findNavController(WireDetails.this)
                            .navigate(R.id.action_restultTabs2_to_checkList);
                }

            });
        }).start());

        //------------------------------------------------------------------------------------------

        Button updateChecklist = v.findViewById(R.id.tabelEndButton);
        updateChecklist.setOnClickListener((View view) -> new Thread(() -> {
            CreationOfPDF pdf = new CreationOfPDF();
            pdf.CreatePDF(NewCustomerFragment.checkList.getCaseNumber());
        }).start());

        //------------------------------------------------------------------------------------------

        Button addButton = v.findViewById(R.id.addRowID);
        addButton.setOnClickListener((View view) -> new Thread(() -> {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    addRow(table1);
                    addRow(table2);
                    addRow(table3);
                }
            });

        }).start());

        //------------------------------------------------------------------------------------------

        Button send = v.findViewById(R.id.send);
        send.setOnClickListener((View view)-> new Thread(() -> {

            WireDetails_Controller UI_Controller = new WireDetails_Controller();

            ArrayList outputList1 = UI_Controller.outputvalue(table1);
            ArrayList outputList2 = UI_Controller.outputvalue(table2);
            ArrayList outputList3 = UI_Controller.outputvalue(table3);


            if (outputList1.size() == outputList2.size() && outputList3.size() == outputList1.size() ){

                ArrayList listOfAllValuesInOrder = UI_Controller.setValuesInOrder(outputList1,outputList2,outputList3);

                UI_Controller.insertTableData(listOfAllValuesInOrder);

//              Change scene

                /*getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        NavHostFragment.findNavController(WireDetails.this)
                                .navigate(R.id.action_restultTabs2_to_mainMenu);
                    }
                });*/
            }
            else{
                System.out.println("please make sure there are the same amount of rows in each table");
            }
        }).start());

    }
    //------------------------------------------------------------------------------------------

    //part of add button
    //      TODO try and use this instead
    //      https://stackoverflow.com/questions/18999601/how-can-i-programmatically-include-layout-in-android
    private void addRow (TableLayout list){
        final View aa = getLayoutInflater().inflate(R.layout.fragment_add_row,null,false);

        //delete row button
        ImageButton cross = aa.findViewById(R.id.cross2);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(aa,list);
            }
        });
        list.addView(aa);
    }

    private void removeView(View aa,TableLayout list) {
        list.removeView(aa);
    }

    //------------------------------------------------------------------------------------------

   /* //part of send button
    private ArrayList outputvalue (TableLayout table ) {
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
//        } conncted to the 1st for loop
        System.out.println("finished collectng all values in layout");
        return arrayList;
    }*/
    //------------------------------------------------------------------------------------------
    /*public ArrayList setValuesInOrder(ArrayList outputList1, ArrayList outputList2,ArrayList outputList3){
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

