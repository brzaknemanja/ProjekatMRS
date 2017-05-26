package com.tim07.service;

import com.tim07.domain.Entity.Chef;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
public interface ChefService {

    boolean createNewChef(Chef chef);

    void updateName(Chef chef);

    void updateLastname(Chef chef);

    void updatePassword(Chef chef);
}
