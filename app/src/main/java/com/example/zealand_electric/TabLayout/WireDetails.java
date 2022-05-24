package com.example.zealand_electric.TabLayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zealand_electric.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WireDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WireDetails extends Fragment {


       @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wire_details, container, false);
    }
}