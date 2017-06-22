package com.tim07.service;

import com.tim07.domain.DTO.OrderItemDTO;
import com.tim07.domain.Entity.OrderItem;
import com.tim07.domain.Entity.Restaurant;
import com.tim07.domain.Entity.TableOrder;
import com.tim07.domain.Enumeration.OrderItemState;

import java.util.List;

/**
 * Created by brzak on 17.6.17..
 */
public interface TableOrderService {

    TableOrder createOrder(List<OrderItemDTO> orderItems, Restaurant restaurant, Long tableId);

    TableOrder finishOrder(Long id);

    OrderItem setItemState(Long id, OrderItemState state);

}
