package com.example.zealand_electric;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import entities.Customer;


public class NewCustomerFragment extends Fragment {

    public String customerName, customerAdress, customerZipCode;
    public static Customer customer= null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_customer, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button doneBtn = view.findViewById(R.id.doneButton);
        Button cancelBtn = view.findViewById(R.id.cancelButton);
        TextInputEditText getcustomerName = view.findViewById(R.id.customerNameText);
        TextInputEditText getcustomerAdress = view.findViewById(R.id.customerAdressText);
        TextInputEditText getcustomerZipCode = view.findViewById(R.id.customerZipCodeText);




        doneBtn.setOnClickListener(v -> new Thread(() -> {
            //TextFromApp
            customerName = getcustomerName.getText().toString();
            customerAdress = getcustomerAdress.getText().toString();
            customerZipCode = getcustomerZipCode.getText().toString();

            Customer customer = new Customer(customerName,customerAdress,customerZipCode);
            Customer customer1 = new Customer("TestMarcus","TestAdress","555");

            //InsertData into DB



           /* DBController.connectToDatabase();
            DBController.insertIntoCustomerTable(customerName,customerAdress,customerZipCode);
            DBController.closeConnection();*/

            //ChangeScene to checklist
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    NavHostFragment.findNavController(NewCustomerFragment.this)
                            .navigate(R.id.action_newCustomerFragment_to_restultTabs2);
                    System.out.println("udskriv customer");


                }
            });


        }).start());

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(NewCustomerFragment.this)
                        .navigate(R.id.action_newCustomerFragment_to_mainMenu);
            }
        });

        };
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //  binding = null;
    }
}