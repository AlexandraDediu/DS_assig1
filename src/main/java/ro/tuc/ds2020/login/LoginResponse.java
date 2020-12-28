package ro.tuc.ds2020.login;

import java.util.Date;
import ro.tuc.ds2020.utils.Role;

public class LoginResponse {

    private String jwt;

    private String username;

    private Role role;

    private Date expirationDate;

    public LoginResponse() {
    }

    public LoginResponse(String jwt, String username, Role role, Date expirationDate) {
        this.jwt = jwt;
        this.username = username;
        this.role = role;
        this.expirationDate = expirationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
