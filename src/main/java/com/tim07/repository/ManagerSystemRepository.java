package com.tim07.repository;

import com.tim07.domain.Entity.ManagerSystem;
import org.springframework.data.repository.Repository;

/**
 * Created by Katarina Cukurov on 09/04/2017.
 */
public interface ManagerSystemRepository extends Repository<ManagerSystem, Long>{

    void save(ManagerSystem managerSystem);

    ManagerSystem findByUsername(String username);
}
