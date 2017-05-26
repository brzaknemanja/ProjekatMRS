package com.tim07.service;

import com.tim07.domain.Entity.Waiter;

/**
 * Created by freez on 10-Apr-17.
 */
public interface WaiterService
{
    boolean createNewUser(Waiter user);

    Waiter getByUsername(String username);

    void updateName(Waiter waiter);

    void updateLastname(Waiter waiter);

    void updatePassword(Waiter waiter);
}
