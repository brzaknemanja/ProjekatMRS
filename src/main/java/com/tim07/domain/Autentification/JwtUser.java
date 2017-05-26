package com.tim07.domain.Autentification;

/**
 * Created by Ivana Zeljkovic on 16-Apr-17.
 */
public class JwtUser {

    private String username;

    public JwtUser() { }

    public JwtUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
