package com.tim07.repository;

import com.tim07.domain.Entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Katarina Cukurov on 30/04/2017.
 */
public interface DishRepository extends JpaRepository<Dish, Long> {

    Dish save(Dish dish);
}
