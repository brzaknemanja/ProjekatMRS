package com.tim07.service;

import com.tim07.domain.Entity.Waiter;
import com.tim07.repository.UserRepository;
import com.tim07.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by freez on 10-Apr-17.
 */
@Service
public class WaiterServiceImpl implements WaiterService
{
    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createNewUser(Waiter user) {
        if(this.userRepository.findByUsername(user.getUsername()) == null) {
            this.waiterRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Waiter getByUsername(String username) {
        return this.waiterRepository.findByUsername(username);
    }

    @Override
    public void updateName(Waiter waiter) {
        this.waiterRepository.save(waiter);
    }

    @Override
    public void updateLastname(Waiter waiter) {
        this.waiterRepository.save(waiter);
    }

    @Override
    public void updatePassword(Waiter waiter) {
        waiter.setFirstLogin(false);
        this.waiterRepository.save(waiter);
    }
}
