package com.example.zealand_electric.TabLayout;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.zealand_electric.CreationOfPDF;
import com.example.zealand_electric.Fragments.NewCustomerFragment;
import com.example.zealand_electric.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WireDetails# newInstance} factory method to
 * create an instance of this fragment.
 */
public class WireDetails extends Fragment {
    EditText value1,value2,value3;
    ArrayList<String> text = new ArrayList<>();
    TableRow.LayoutParams boxsize = new TableRow.LayoutParams(300,150);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
// Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_wire_details, container,false);

        //creating subtext
        //can't make it 1 line. dk how
        TextView Crosscut_Value = v.findViewById(R.id.Crosscut_Value);
        Crosscut_Value.setText(Html.fromHtml("mm<sub>2</sub>"));

        TextView OB_Value = v.findViewById(R.id.OB_Value);
        OB_Value.setText(Html.fromHtml("OB (L<sub>n</sub>)"));

        TextView MaxOB_Value = v.findViewById(R.id.MaxOB_Value);
        MaxOB_Value.setText(Html.fromHtml("Maks.OB (L<sub>n</sub>)"));

          /* //Test
           //------------------------------------------------------------------------------------------

           //need to study up on this cuz im not sure what this is or which is better, susanne :D
           //TODO
           //makes a table row and adds edit texts to it
           TableRow valueRow0 = new TableRow(getContext());
           // might not be needed
           //valueRow0.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));



           EditText txtv0 = new EditText(getContext());
           txtv0.setTextSize(20);
           txtv0.setHint("NO");
           txtv0.setLayoutParams(boxsize);
           valueRow0.addView(txtv0);


           EditText txtv1 = new EditText(getActivity());
           txtv1.setLayoutParams(boxsize);
           valueRow0.addView(txtv1);
           txtv1.setHint("nice!");

           EditText txtv2 = new EditText((getContext()));
           txtv2.setLayoutParams(boxsize);
           valueRow0.addView(txtv2);
           txtv2.setHint("yuuubie");


           TableLayout tabel1 = v.findViewById(R.id.Kredsdetalje_Tabel1_value);
           System.out.println("tabelayout 1");
           tabel1.addView(valueRow0);
           //------------------------------------------------------------------------------------------
*/

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        TableLayout list1 = v.findViewById(R.id.Kredsdetalje_Tabel1_value);
        TableLayout list2 = v.findViewById(R.id.Kredsdetalje_Tabel2_value);

        //buttons - back to checklist
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


        Button updateChecklist = v.findViewById(R.id.tabelEndButton);
        updateChecklist.setOnClickListener((View view) -> new Thread(() -> {
            CreationOfPDF pdf = new CreationOfPDF();
            pdf.CreatePDF(NewCustomerFragment.checkList.getCaseNumber());

        }).start());


        Button addButton = v.findViewById(R.id.addRowID);
        addButton.setOnClickListener((View view) -> new Thread(() -> {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    addRow(list1);
                    addRow(list2);
                }
            });

        }).start());

        Button test = v.findViewById(R.id.send);
        test.setOnClickListener((View view)-> new Thread(() -> {
            outputvalue(list1,v);

        }).start());


        //------------------------------------------------------------------------------------------
        //clear row button is part of addrow
        //buttons end
    }



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



    private void outputvalue (TableLayout table, View v) {
        //pass View v as well and call the specific layout
        //LinearLayoutCompat ll = v.findViewById(R.id.linearLayout_ID);
        LinearLayoutCompat ll = (LinearLayoutCompat) table.getChildAt(0);
//        for (int i = 0; i < table.getChildCount(); i++) {

            //if (table.getChildAt(i) instanceof LinearLayoutCompat) {

                for (int j = 0; j  < ll.getChildCount(); j++) {
                    if (ll.getChildAt(j)instanceof TableRow){
                        TableRow tr = (TableRow) ll.getChildAt(j);

                        for (int k = 0; k < tr.getChildCount() -1 ; k++) {
                            EditText et = (EditText) tr.getChildAt(k);

                            //code to change sout into arraylist that sends data into DB
                            System.out.println(et.getText().toString());
                        }

                    }
                }
//        } conncted to the 1st for loop
    }
}

