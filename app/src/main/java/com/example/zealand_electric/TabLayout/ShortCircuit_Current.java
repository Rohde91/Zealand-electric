package com.example.zealand_electric.TabLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.zealand_electric.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShortCircuit_Current# newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShortCircuit_Current extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_short_circuit__current, container, false);
    }
}