package com.tim07.service;

import com.tim07.domain.Entity.User;

/**
 * Created by Ivana Zeljkovic on 08-Apr-17.
 */
public interface UserService {

    User findByUsername(String username);

    Boolean authenticate(String username, String password);
}
