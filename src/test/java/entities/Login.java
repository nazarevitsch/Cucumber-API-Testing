package entities;

import io.cucumber.java.DataTableType;

public class Login {
    private String username;
    private String password;

    public Login(String email, String password) {
        this.username = email;
        this.password = password;
    }

    public Login() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
