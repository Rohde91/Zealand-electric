package com.example.zealand_electric.Fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.zealand_electric.Controllers.DBController;
import com.example.zealand_electric.R;

import java.util.Locale;


public class AddUserFragment extends Fragment  {

    public Spinner role;
    public EditText fullName, username, password;
    public String fullNametxt, usernametxt,passwordtxt, roletxt;
    public Button createUserbtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_user, container, false);
        role = rootView.findViewById(R.id.roleSpinner);
        roleSpinner();

        return rootView;
    }

    public void roleSpinner(){
        String[] roles = new String[] {"Elev","Lærer"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, roles);
        role.setAdapter(adapter);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TextEdits, rolespinner and button
        fullName = view.findViewById(R.id.fullNameText);
        username = view.findViewById(R.id.newUserInput);
        password = view.findViewById(R.id.newUserPassInput);
        role = view.findViewById(R.id.roleSpinner);
        createUserbtn = view.findViewById(R.id.addUserBtn);

        createUserbtn.setOnClickListener(v -> new Thread(() -> {
            //TextFromApp
            fullNametxt = fullName.getText().toString();
            usernametxt = username.getText().toString();
            passwordtxt = password.getText().toString();
            roletxt = role.getSelectedItem().toString();

            //Check if the edittext is empty if empty make toast else insert data to db and change scene
            if(TextUtils.isEmpty(fullNametxt)|| TextUtils.isEmpty(usernametxt) || TextUtils.isEmpty(passwordtxt)){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Du mangler at udfylde felterne", Toast.LENGTH_SHORT).show();
                    }
                });

            }else{
                //InsertData into DB / add user
                DBController.connectToDatabase();

                DBController.insertIntoUser(fullNametxt, usernametxt.toLowerCase(Locale.ROOT), passwordtxt, roletxt);

                DBController.closeConnection();

                //Change scene and make Toast
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Ny bruger oprettet", Toast.LENGTH_SHORT).show();
                        NavHostFragment.findNavController(AddUserFragment.this)
                                .navigate(R.id.action_addUserFragment_to_mainMenu);
                    }
                });
            }
        }).start());
    }
}