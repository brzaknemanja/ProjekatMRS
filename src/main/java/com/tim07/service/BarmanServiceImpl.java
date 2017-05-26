package com.tim07.service;

import com.tim07.domain.Entity.Barman;
import com.tim07.repository.BarmanRepository;
import com.tim07.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
@Service
public class BarmanServiceImpl implements BarmanService{

    @Autowired
    BarmanRepository barmanRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createNewBarman(Barman barman)
    {
        if(this.userRepository.findByUsername(barman.getUsername()) == null)
        {
            this.barmanRepository.save(barman);
            return true;
        }
        return false;
    }

    @Override
    public void updateName(Barman barman) {
        this.barmanRepository.save(barman);
    }

    @Override
    public void updateLastname(Barman barman) {
        this.barmanRepository.save(barman);
    }

    @Override
    public void updatePassword(Barman barman) {
        barman.setFirstLogin(false);
        this.barmanRepository.save(barman);

    }
}
