package com.tim07.service;

import com.tim07.domain.Entity.Barman;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
public interface BarmanService {

    boolean createNewBarman(Barman barman);

    void updateName(Barman barman);

    void updateLastname(Barman barman);

    void updatePassword(Barman barman);
}
