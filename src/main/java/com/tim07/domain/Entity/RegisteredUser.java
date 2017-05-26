package com.tim07.domain.Entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.serializer.CustomFriendshipSerializer;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivana Zeljkovic on 08-Apr-17.
 */
@Entity
public class RegisteredUser extends User implements Serializable {

    @Column(nullable = false)
    @Email
    private String email;

    @OneToMany(mappedBy = "firstUser", cascade = CascadeType.ALL)
    @JsonSerialize(using = CustomFriendshipSerializer.class)
    private Map<RegisteredUser, Friendship> friendships;

    public RegisteredUser() { }

    public RegisteredUser(String username, String password, UserType type, String name, String lastname, String email) {
        super(name, lastname, username, password, type);
        this.email = email;
        this.friendships = new HashMap<RegisteredUser, Friendship>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<RegisteredUser, Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships(Map<RegisteredUser, Friendship> friendships) {
        this.friendships = friendships;
    }

    @Override
    public boolean equals(Object regUserObj) {
        RegisteredUser regUser = (RegisteredUser)regUserObj;
        if(this.getUsername() == regUser.getName() && this.getLastname() == regUser.getLastname()
                && this.getUsername() == regUser.getUsername()
                && this.getPassword() == regUser.getPassword()
                && this.getType() == regUser.getType()
                && this.email == regUser.getEmail()
                && this.friendships.equals(regUser.getFriendships()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "name: " + this.getName() +
                ", lastname: " + this.getLastname() +
                ", username: " + this.getUsername() +
                ", password: " + this.getPassword() +
                ", email: " + this.email +
                ", friendships: " + this.getFriendships().size() +
                "}";
    }
}
