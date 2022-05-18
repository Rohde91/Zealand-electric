package com.example.zealand_electric;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link confirm_ChecklistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class confirm_ChecklistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public confirm_ChecklistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment confirm_Checklist.
     */
    // TODO: Rename and change types and number of parameters
    public static confirm_ChecklistFragment newInstance(String param1, String param2) {
        confirm_ChecklistFragment fragment = new confirm_ChecklistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm__checklist, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView back = view.findViewById(R.id.confirmToMain);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(confirm_ChecklistFragment.this)
                        .navigate(R.id.action_confirm_Checklist_to_mainMenu);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //  binding = null;
    }
}