package com.example.zealand_electric.TabLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.zealand_electric.R;
import com.google.android.material.tabs.TabLayout;


public class RestultTabs2 extends Fragment {



    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restult_tabs2, container, false);
        tabLayout = view.findViewById(R.id.Tablayout);
        viewPager = view.findViewById(R.id.ViewPager);
        tabLayout.setupWithViewPager(viewPager);
        Fragment wireDetails = new WireDetails();

        // change it from new fragment to newinstance
        // - guide -
        // https://stackoverflow.com/questions/9245408/best-practice-for-instantiating-a-new-android-fragment
        ResultTabAdapter resultTabAdapter = new ResultTabAdapter(requireActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        resultTabAdapter.addFragment(wireDetails, "Kredsdetaljer");
        resultTabAdapter.addFragment(new rcd_Test(), "RCD Test");
        resultTabAdapter.addFragment(new ShortCircuit_Current(), "Kortslutning strøm");
        viewPager.setAdapter(resultTabAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}