package com.tim07.repository;

import com.tim07.domain.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by brzak on 17.6.17..
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
