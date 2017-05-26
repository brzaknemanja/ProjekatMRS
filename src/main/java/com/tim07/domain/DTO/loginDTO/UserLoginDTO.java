package com.tim07.domain.DTO.loginDTO;

import java.io.Serializable;

/**
 * Created by Ivana Zeljkovic on 08-Apr-17.
 */
public class UserLoginDTO implements Serializable {
    private String username;

    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
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
