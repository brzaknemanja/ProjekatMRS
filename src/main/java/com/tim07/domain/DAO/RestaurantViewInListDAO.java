package com.tim07.domain.DAO;

import com.tim07.domain.Enumeration.KitchenType;

/**
 * Created by Ivana Zeljkovic on 30-Apr-17.
 */
public interface RestaurantViewInListDAO {
    Long getId();
    String getName();
    String getDescription();
    String getStreet();
    String getCity();
    KitchenType getKitchenType();
}
