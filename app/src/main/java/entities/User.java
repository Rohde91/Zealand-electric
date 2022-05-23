package entities;

public class User {

    protected int id;
    protected String fullName;
    protected String username;
    protected String password;
    protected String userRole;
    public User() {}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }


    //constructor to create a new user
    public User(int id, String fullName, String username, String password, String userRole){
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }









    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getUserRole() { return userRole; }

    public void setUserRole(String userRole) { this.userRole = userRole; }
}
