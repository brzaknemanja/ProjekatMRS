package com.tim07.service;

import com.tim07.domain.Entity.ManagerSystem;
import com.tim07.repository.ManagerSystemRepository;
import com.tim07.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Katarina Cukurov on 09/04/2017.
 */

@Service
public class ManagerSystemServiceImpl implements ManagerSystemService {

    @Autowired
    private ManagerSystemRepository managerSystemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createNewManager(ManagerSystem managerSystem)
    {
        if(this.userRepository.findByUsername(managerSystem.getUsername()) == null)
        {
            this.managerSystemRepository.save(managerSystem);
            return true;
        }
        return false;
    }

    @Override
    public ManagerSystem getByUsername(String username)
    {
        return this.managerSystemRepository.findByUsername(username);
    }
}
