package com.tim07.service;


import com.tim07.domain.Entity.User;
import com.tim07.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ivana Zeljkovic on 08-Apr-17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username){
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Boolean authenticate(String username, String password){
        User user = findByUsername(username);
        if(user != null){
            return user.getPassword().equals(password);
        }
        return false;
    }
}
