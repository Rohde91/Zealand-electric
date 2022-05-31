package entities;

import java.util.ArrayList;

public class Customer {

    protected int id;
    protected String customerName;
    protected String customerAdress;
    protected String fk_zipCode;
    protected ArrayList<CheckList> checklistTabel;

    public ArrayList<CheckList> getChecklistTabel() {
        return checklistTabel;
    }

    public void setChecklistTabel(ArrayList<CheckList> checklistTabel) {
        this.checklistTabel = checklistTabel;
    }

    public Customer(int id, String customerName, String customerAdress, String fk_zipCode) {
        this.id = id;
        this.customerName = customerName;
        this.customerAdress = customerAdress;
        this.fk_zipCode = fk_zipCode;
    }


    //used in newCstomerFragment to create a new customer, doesn't need id because it will auto increment
    public Customer(String customerName, String customerAdress, String customerZipCode) {
        this.customerName = customerName;
        this.customerAdress = customerAdress;
        this.fk_zipCode = customerZipCode;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAdress() {
        return customerAdress;
    }

    public void setCustomerAdress(String customerAdress) {
        this.customerAdress = customerAdress;
    }

    public String getFk_zipCode() {
        return fk_zipCode;
    }

    public void setFk_zipCode(String fk_zipCode) {
        this.fk_zipCode = fk_zipCode;
    }
}
