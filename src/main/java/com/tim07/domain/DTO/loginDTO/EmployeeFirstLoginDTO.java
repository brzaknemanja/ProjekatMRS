package com.tim07.domain.DTO.loginDTO;

import java.io.Serializable;

/**
 * Created by freez on 29-Apr-17.
 */
public class EmployeeFirstLoginDTO implements Serializable {
    private String username;

    private String password;

    private String newPassword;

    public EmployeeFirstLoginDTO() {
    }

    public EmployeeFirstLoginDTO(String username, String password, String newPassword) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
