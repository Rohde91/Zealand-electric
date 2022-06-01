package com.example.zealand_electric.TabLayout;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zealand_electric.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WireDetails# newInstance} factory method to
 * create an instance of this fragment.
 */
public class WireDetails extends Fragment {


       @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
           View v = inflater.inflate(R.layout.fragment_wire_details, container, false);

           //creating the layout
           //it is possible to make it 1 line but can't make it work
           TextView Crosscut_Value = v.findViewById(R.id.Crosscut_Value);
           Crosscut_Value.setText(Html.fromHtml("mm<sub>2</sub>"));

           TextView OB_Value = v.findViewById(R.id.OB_Value);
           OB_Value.setText(Html.fromHtml("OB (L<sub>n</sub>)"));

           TextView MaxOB_Value = v.findViewById(R.id.MaxOB_Value);
           MaxOB_Value.setText(Html.fromHtml("Maks.OB (L<sub>n</sub>)"));

           return v;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        //need to study up on this cuz im not sure what this is or which is better, susanne :D
        //TODO
        TableRow tbrow0 = new TableRow(getContext());
        TextView tv0 = new TextView(getActivity());

        /*TabLayout Kredsdetalje_Tabel1_value = v.findViewById(R.id.Kredsdetalje_Tabel1_value);
        Kredsdetalje_Tabel1_value.addTab(



        );*/

    }
}