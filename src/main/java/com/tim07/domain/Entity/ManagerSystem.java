package com.tim07.domain.Entity;

import com.tim07.domain.Enumeration.UserType;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Katarina Cukurov on 09/04/2017.
 */
@Entity
public class ManagerSystem extends User implements  Serializable{

    @Column(nullable = false)
    @NotNull
    @Email
    private String email;

    public ManagerSystem(){ }

    public ManagerSystem(String username, String password, UserType type, String name, String lastname, String email)
    {
        super(name, lastname, username, password, type);
        this.email = email;
    }

    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    @Override
    public boolean equals(Object manager)
    {
        ManagerSystem managerSystem = (ManagerSystem) manager;
        if((this.getName() == managerSystem.getName()) && (this.getLastname() == managerSystem.getLastname())
                && (this.email == managerSystem.getEmail()) && (this.getUsername() == managerSystem.getUsername())
                && (this.getPassword() == managerSystem.getPassword()) && (this.getType() == managerSystem.getType())){
            return true;
        }
        return false;
    }
}
