package com.tim07.domain.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tim07.domain.Entity.Friendship;
import com.tim07.domain.Entity.RegisteredUser;
import com.tim07.domain.Enumeration.UserType;
import com.tim07.serializer.CustomFriendshipSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivana Zeljkovic on 17-Apr-17.
 */
public class RegisteredUserDTO {
    private String name;
    private String lastname;
    private String username;
    private String email;
    private UserType type;
    @JsonSerialize(using = CustomFriendshipSerializer.class)
    private Map<RegisteredUser, Friendship> friendships;

    public RegisteredUserDTO() { }

    public RegisteredUserDTO(String name, String lastname, String username, String email, HashMap<RegisteredUser, Friendship> friendships) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.type = UserType.Registered;
        this.friendships = friendships;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Map<RegisteredUser, Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships(Map<RegisteredUser, Friendship> friendships) {
        this.friendships = friendships;
    }
}
