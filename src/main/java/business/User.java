package business;

import java.util.Objects;

public class User {
    // Properties
    private String username;
    private String password;
    private String AdminStatus;

    //CONSTRUCTORS
    // No Parameter constructor
    public User(){

    }

    //  Parameterised constructor
    public User(String username, String password, String adminStatus){
        this.username = username;
        this.password = password;
        this.AdminStatus =adminStatus;
    }

    //GETTERS & SETTERS


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdminStatus() {
        return AdminStatus;
    }

    public void setAdminStatus(String adminStatus) {
        AdminStatus = adminStatus;
    }

    // GOOD PRACTICE or HELPER METHODS
    @Override
    public String toString() {
        return "business.User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", AdminStatus='" + AdminStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    //Validate password method
    public boolean validatePassword(String password){
        return this.password.equals(password);
    }
}
