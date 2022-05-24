package com.example.zealand_electric;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;

import entities.Customer;
import entities.User;


public class NewCustomerFragment extends Fragment {

    public String customerName, customerAdress, customerZipCode, orderNumber, installationLocation, installer;
    public Customer customer;
    public int customerId;
    public User user = LoginFragment.user;
    @SuppressLint("NewApi")
    public static LocalDate date = LocalDate.now();


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
        TextInputEditText getOrdernumber = view.findViewById(R.id.ordernNumberText);
        TextInputEditText getInstallationlocation = view.findViewById(R.id.installationIDText);
        TextInputEditText getInstaller = view.findViewById(R.id.installedByText);

        doneBtn.setOnClickListener(v -> new Thread(() -> {
            //TextFromApp
            customerName = getcustomerName.getText().toString();
            customerAdress = getcustomerAdress.getText().toString();
            customerZipCode = getcustomerZipCode.getText().toString();
            orderNumber = getOrdernumber.getText().toString();
            installationLocation = getInstallationlocation.getText().toString();
            installer = getInstaller.getText().toString();

            //InsertData into DB

            if(TextUtils.isEmpty(customerName)|| TextUtils.isEmpty(customerAdress)
                    || TextUtils.isEmpty(customerZipCode) || TextUtils.isEmpty(orderNumber) ||
                    TextUtils.isEmpty(installationLocation) || TextUtils.isEmpty(installer)){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Du mangler at udfylde felterne", Toast.LENGTH_SHORT).show();
                    }
                });
            }else {

                customer = new Customer(customerName, customerAdress, customerZipCode);

                DBController.connectToDatabase();
                customerId = DBController.insertIntoCustomerTableAndReturnID(customer);
                DBController.insertIntoCheckList(customerId, date, orderNumber,
                        installationLocation, installer, user.getId(), 0, 0);
                DBController.closeConnection();

                //ChangeScene
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NavHostFragment.findNavController(NewCustomerFragment.this)
                                .navigate(R.id.action_newCustomerFragment_to_checkList);
                    }
                });
            }

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