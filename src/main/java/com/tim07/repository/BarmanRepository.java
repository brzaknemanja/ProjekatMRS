package com.tim07.repository;

import com.tim07.domain.Entity.Barman;
import org.springframework.data.repository.Repository;

/**
 * Created by Katarina Cukurov on 17/04/2017.
 */
public interface BarmanRepository extends Repository<Barman, Long> {

    Barman findByUsername(String username);

    void save(Barman barman);
}
