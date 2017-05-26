package com.tim07.domain.DTO;

import com.tim07.domain.Entity.Friendship;
import com.tim07.domain.Enumeration.FriendshipStatus;

/**
 * Created by Ivana Zeljkovic on 14-Apr-17.
 */
public class FriendDTO {

    private String name;
    private String lastname;
    private String username;
    private FriendshipStatus status;

    public FriendDTO() { }

    public FriendDTO(String name, String lastname, String username, FriendshipStatus status) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.status = status;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public FriendshipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendshipStatus status) {
        this.status = status;
    }
}
