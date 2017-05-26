package com.tim07.service;

import com.tim07.domain.Entity.ManagerSystem;

/**
 * Created by Katarina Cukurov on 09/04/2017.
 */
public interface ManagerSystemService {

    boolean createNewManager(ManagerSystem managerSystem);

    ManagerSystem getByUsername(String username);
}
