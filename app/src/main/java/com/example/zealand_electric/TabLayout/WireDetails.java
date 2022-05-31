package com.example.zealand_electric.TabLayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zealand_electric.R;


public class WireDetails extends Fragment {

       @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.fragment_wire_details, container, false);

           TextView OB_Value = v.findViewById(R.id.OB_Value);
           OB_Value.setText(Html.fromHtml("OB(L<sub>n</sub>)"));

           TextView MaxOB_Value = v.findViewById(R.id.MaxOB_Value);
           MaxOB_Value.setText(Html.fromHtml("MaxOB(L<sub>n</sub>)"));

           TextView Crosscut_Value = v.findViewById(R.id.Crosscut_Value);
           Crosscut_Value.setText(Html.fromHtml("mm<sup>2<sup>"));
           return v;
    }
}