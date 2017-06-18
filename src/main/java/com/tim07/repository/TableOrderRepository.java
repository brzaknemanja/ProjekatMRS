package com.tim07.repository;

import com.tim07.domain.Entity.TableOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by brzak on 17.6.17..
 */
public interface TableOrderRepository extends JpaRepository<TableOrder, Long> {

}
