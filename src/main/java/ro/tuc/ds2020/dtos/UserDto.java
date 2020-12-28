package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.UserEntity;
import ro.tuc.ds2020.utils.Role;

public class UserDto {

    private Long id;

    private String username;

    private String password;

    private Role role;

    private Boolean active;

    public UserDto() {
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDto(Long id, String username, String password, Role role, Boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UserEntity convertToEntity() {
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setActive(active);
        return user;
    }
}
