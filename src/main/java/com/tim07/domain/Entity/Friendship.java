package com.tim07.domain.Entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tim07.domain.Enumeration.FriendshipStatus;
import com.tim07.serializer.CustomFriendSerializer;

import javax.persistence.*;

/**
 * Created by Ivana Zeljkovic on 16-Apr-17.
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"first_user_id", "second_user_id"}))
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonSerialize(using = CustomFriendSerializer.class)
    private RegisteredUser firstUser;

    @ManyToOne
    @JsonSerialize(using = CustomFriendSerializer.class)
    private RegisteredUser secondUser;

    @Column
    private FriendshipStatus status;

    public Friendship() { }

    public Friendship(RegisteredUser firstUser, RegisteredUser secondUser, FriendshipStatus status) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
        this.status = status;
    }

    public RegisteredUser getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(RegisteredUser firstUser) {
        this.firstUser = firstUser;
    }

    public RegisteredUser getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(RegisteredUser secondUser) {
        this.secondUser = secondUser;
    }

    public FriendshipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendshipStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        Friendship fObj = (Friendship)obj;
        if(this.firstUser.equals(fObj.getFirstUser()) && this.secondUser.equals(fObj.getSecondUser()) && this.status.equals(fObj.getStatus()))
            return true;
        return false;
    }
}
