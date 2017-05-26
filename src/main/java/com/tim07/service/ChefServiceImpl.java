package com.tim07.service;

import com.tim07.domain.Entity.Chef;
import com.tim07.repository.ChefRepository;
import com.tim07.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@Service
public class ChefServiceImpl implements ChefService{

    @Autowired
    ChefRepository chefRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createNewChef(Chef chef)
    {
        if(this.userRepository.findByUsername(chef.getUsername()) == null)
        {
            this.chefRepository.save(chef);
            return true;
        }
        return false;
    }

    @Override
    public void updateName(Chef chef) {
        this.chefRepository.save(chef);
    }

    @Override
    public void updateLastname(Chef chef) {
        this.chefRepository.save(chef);
    }

    @Override
    public void updatePassword(Chef chef) {
        chef.setFirstLogin(false);
        this.chefRepository.save(chef);
    }
}
