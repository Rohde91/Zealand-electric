package com.example.zealand_electric.TabLayout;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.zealand_electric.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WireDetails# newInstance} factory method to
 * create an instance of this fragment.
 */
public class WireDetails extends Fragment {
    EditText value1,value2,value3;
    ConstraintLayout list;
    ArrayList<String> text = new ArrayList<>();
    TableRow.LayoutParams boxsize = new TableRow.LayoutParams(300,150);
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
        //buttons - back to checklist
        //------------------------------------------------------------------------------------------

        Button addButton = v.findViewById(R.id.addRowID);
        addButton.setOnClickListener((View view) -> new Thread(() -> {
            addRow();

        }).start());










        Button deleteRow = v.findViewById(R.id.DeleteRowID);

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





         // UpdateChecklist
        Button updateChecklist = v.findViewById(R.id.tabelEndButton);





        //------------------------------------------------------------------------------------------
        //buttons end


        //------------------------------------------------------------------------------------------

    }
    private void addRow(){
        //final View aa = getLayoutInflater().inflate(fragment_add_row,null,false);
        final View aa = getLayoutInflater().inflate(R.layout.fragment_add_row,null,false);
        Button cross = aa.findViewById(R.id.cross2);
       /* cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent
            }
        });*/
        list.addView(aa);
    }

     /*Find Tablelayout defined in main.xml
    TableLayout tl = (TableLayout) findViewById(R.id.SaleOrderLines);

     //Create a new row to be added.
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        //Create a Button to be the row-content.
        Button b = new Button(this);
        b.setText("Dynamic Button");
        b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

      //Add Button to row.
        tr.addView(b);

    //Add row to TableLayout.
    tr.setBackgroundResource(R.drawable.sf_gradient_03);
    tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));*/

}

