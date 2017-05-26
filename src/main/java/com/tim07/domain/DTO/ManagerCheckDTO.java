package com.tim07.domain.DTO;


/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
public class ManagerCheckDTO {

    private String name;
    private String lastname;
    private String username;

    public ManagerCheckDTO(){}

    public ManagerCheckDTO(String name, String lastname, String username) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}
