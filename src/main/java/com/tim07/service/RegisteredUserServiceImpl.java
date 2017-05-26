package com.tim07.service;

import com.tim07.domain.DAO.RegisteredUserSearchDAO;
import com.tim07.domain.Entity.Friendship;
import com.tim07.domain.Entity.RegisteredUser;
import com.tim07.domain.Enumeration.FriendshipStatus;
import com.tim07.repository.FriendshipRepository;
import com.tim07.repository.RegisteredUserRepository;
import com.tim07.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ivana Zeljkovic on 09-Apr-17.
 */
@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createNewUser(RegisteredUser user){
        if(this.userRepository.findByUsername(user.getUsername()) == null) {
            this.registeredUserRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<RegisteredUserSearchDAO> findUsers(String username, String parameter){
        return this.registeredUserRepository.findUsers(username, parameter);
    }

    @Override
    public RegisteredUser getByUsername(String username){
        return this.registeredUserRepository.findByUsername(username);
    }

    @Override
    public void updateName(RegisteredUser user){
       this.registeredUserRepository.save(user);
    }

    @Override
    public void updateLastname(RegisteredUser user){
        this.registeredUserRepository.save(user);
    }

    @Override
    public void updatePassword(RegisteredUser user){
        this.registeredUserRepository.save(user);
    }

    @Override
    public void addFriend(RegisteredUser user, RegisteredUser friend)
    {
        Friendship friendship = new Friendship(user, friend, FriendshipStatus.Pending);
        Friendship inverse = new Friendship(friend, user, null);
        user.getFriendships().put(friend, friendship);
        friend.getFriendships().put(user, inverse);

        this.registeredUserRepository.save(user);
        this.registeredUserRepository.save(friend);
    }

    @Override
    public void removeFriend(RegisteredUser user, RegisteredUser friend){
        Friendship friendship = user.getFriendships().get(friend);
        Friendship inverse = friend.getFriendships().get(user);

        user.getFriendships().remove(friend);
        friend.getFriendships().remove(user);

        this.friendshipRepository.delete(friendship);
        this.friendshipRepository.delete(inverse);
        this.registeredUserRepository.save(user);
        this.registeredUserRepository.save(friend);
    }

    @Override
    public boolean acceptFriendRequest(RegisteredUser user, RegisteredUser friend){
        if(!user.getFriendships().containsKey(friend) || user.getFriendships().get(friend).getStatus() != null)
            return false;
        user.getFriendships().get(friend).setStatus(FriendshipStatus.Accepted);
        friend.getFriendships().get(user).setStatus(FriendshipStatus.Accepted);

        this.registeredUserRepository.save(user);
        this.registeredUserRepository.save(friend);
        return true;
    }

    @Override
    public boolean deleteFriendRequest(RegisteredUser user, RegisteredUser friend){
        if(!user.getFriendships().containsKey(friend) || user.getFriendships().get(friend).getStatus() != null)
            return false;
        Friendship friendship = user.getFriendships().get(friend);
        Friendship inverse = friend.getFriendships().get(user);

        user.getFriendships().remove(friend);
        friend.getFriendships().remove(user);

        this.friendshipRepository.delete(friendship);
        this.friendshipRepository.delete(inverse);
        this.registeredUserRepository.save(user);
        this.registeredUserRepository.save(friend);
        return true;
    }
}
