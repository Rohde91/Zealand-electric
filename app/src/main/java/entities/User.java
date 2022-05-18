package entities;

public class User {

    protected int id;
    protected String name;
    protected String email;
    protected String phone;
    protected String address;
    protected String password;
    protected String userRole;
    public User() {}

    public User(int id, String name, String email, String phone, String address, String password, String userRole){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.userRole = userRole;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getUserRole() { return userRole; }

    public void setUserRole(String userRole) { this.userRole = userRole; }
}
