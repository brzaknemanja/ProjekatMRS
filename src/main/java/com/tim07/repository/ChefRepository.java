package com.tim07.repository;

import com.tim07.domain.Entity.Chef;
import org.springframework.data.repository.Repository;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
public interface ChefRepository extends Repository<Chef, Long> {

    void save(Chef chef);

    Chef findByUsername(String username);
}
