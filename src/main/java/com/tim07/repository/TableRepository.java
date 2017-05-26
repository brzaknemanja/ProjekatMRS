package com.tim07.repository;

import com.tim07.domain.Entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Katarina Cukurov on 22/04/2017.
 */
public interface TableRepository extends JpaRepository<RestaurantTable, Long> {

    RestaurantTable save(RestaurantTable table);
}
