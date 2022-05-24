package com.example.zealand_electric.TabLayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zealand_electric.R;
import com.google.android.material.tabs.TabLayout;

public class ResultTabs extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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

