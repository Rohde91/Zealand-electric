package com.example.zealand_electric;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddUserFragment extends Fragment {

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

            //InsertData into DB / add user


            DBController.connectToDatabase();

            DBController.insertIntoUser(fullNametxt,usernametxt,passwordtxt,roletxt);

            DBController.closeConnection();

            //ChangeScene and make Toast

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "Ny bruger oprettet", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(AddUserFragment.this)
                            .navigate(R.id.action_addUserFragment_to_mainMenu);
                }
            });



        }).start());




    }
}