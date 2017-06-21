package com.tim07.service;

import com.tim07.domain.DTO.OrderItemDTO;
import com.tim07.domain.Entity.OrderItem;
import com.tim07.domain.Entity.Restaurant;
import com.tim07.domain.Entity.TableOrder;
import com.tim07.domain.Enumeration.OrderItemState;
import com.tim07.repository.OrderItemRepository;
import com.tim07.repository.TableOrderRepository;
import com.tim07.repository.TableRepository;
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

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public TableOrder createOrder(List<OrderItemDTO> orderItems, Restaurant restaurant, Long tableId) {

        List<OrderItem> orderItemsList = new ArrayList<>();
        TableOrder tableOrder = new TableOrder();

        for(OrderItemDTO item : orderItems){
            orderItemsList.add(new OrderItem(item.getName(),item.getPrice(),item.getType(),item.getAmount(),tableOrder));
        }

        tableOrder.setOrderItems(orderItemsList);
        tableOrder.setRestaurant(restaurant);
        tableOrder.setRestaurantTable(tableRepository.getOne(tableId));

        return tableOrderRepository.save(tableOrder);

    }

    public OrderItem setItemState(Long id, OrderItemState state)
    {
        OrderItem item = orderItemRepository.findById(id);
        item.setState(state);
        return orderItemRepository.save(item);
    }
}
