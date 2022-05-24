package com.example.zealand_electric.TabLayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zealand_electric.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RestultTabs2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestultTabs2 extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;
/*

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RestultTabs2() {
        // Required empty public constructor
    }

    */
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestultTabs2.
     */
    /*
    // TODO: Rename and change types and number of parameters
    public static RestultTabs2 newInstance(String param1, String param2) {
        RestultTabs2 fragment = new RestultTabs2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
            }
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restult_tabs2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}*/

        setContentView(R.layout.fragment_result_tabs);

        tabLayout = findViewById(R.id.Tablayout);
        viewPager = findViewById(R.id.ViewPager);
        tabLayout.setupWithViewPager(viewPager);

        ResultTabAdapter resultTabAdapter = new ResultTabAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        resultTabAdapter.addFragment(new WireDetails(), "Kredsdetaljer");
        resultTabAdapter.addFragment(new rcd_Test(), "RCD test");
        resultTabAdapter.addFragment(new ShortCircuit_Current(), "Kortslutning strøm");
        viewPager.setAdapter(resultTabAdapter);
    }
}