package com.tim07.service;

import com.tim07.domain.DTO.OrderItemDTO;
import com.tim07.domain.Entity.OrderItem;
import com.tim07.domain.Entity.Restaurant;
import com.tim07.domain.Entity.TableOrder;
import com.tim07.repository.TableOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by brzak on 17.6.17..
 */
@Service
public class TableOrderServiceImpl implements TableOrderService {

    @Autowired
    private TableOrderRepository tableOrderRepository;

    public TableOrder createOrder(List<OrderItemDTO> orderItems, Restaurant restaurant) {

        List<OrderItem> orderItemsList = new ArrayList<>();
        TableOrder tableOrder = new TableOrder();

        for(OrderItemDTO item : orderItems){
            orderItemsList.add(new OrderItem(item.getName(),item.getPrice(),item.getType(),item.getAmount(),tableOrder));
        }

        tableOrder.setOrderItems(orderItemsList);
        tableOrder.setRestaurant(restaurant);

        return tableOrderRepository.save(tableOrder);

    }
}
