package com.tim07.service;

import com.tim07.domain.DAO.RegisteredUserSearchDAO;
import com.tim07.domain.Entity.RegisteredUser;

import java.util.List;

/**
 * Created by Ivana Zeljkovic on 09-Apr-17.
 */
public interface RegisteredUserService {
    boolean createNewUser(RegisteredUser user);

    List<RegisteredUserSearchDAO> findUsers(String username, String parameter);

    RegisteredUser getByUsername(String username);

    void updateName(RegisteredUser user);

    void updateLastname(RegisteredUser user);

    void updatePassword(RegisteredUser user);

    void addFriend(RegisteredUser user, RegisteredUser friend);

    void removeFriend(RegisteredUser user, RegisteredUser friend);

    boolean deleteFriendRequest(RegisteredUser user, RegisteredUser friend);

    boolean acceptFriendRequest(RegisteredUser user, RegisteredUser friend);
}
