package com.tim07.repository;

import com.tim07.domain.Entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Katarina Cukurov on 30/04/2017.
 */
public interface DrinkRepository extends JpaRepository<Drink, Long> {

    Drink save(Drink drink);
}
