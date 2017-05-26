package com.tim07.repository;


import com.tim07.domain.Entity.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by freez on 10-Apr-17.
 */
public interface WaiterRepository extends JpaRepository<Waiter, Long>
{
    Waiter save(Waiter user);

    Waiter findByUsername(String username);

}
