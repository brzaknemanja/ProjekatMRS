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
            if(item.getAmount() <= 0)
                return null;
            orderItemsList.add(new OrderItem(item.getName(), item.getDescription(),item.getPrice(),item.getType(),item.getAmount(),tableOrder));
        }

        tableOrder.setOrderItems(orderItemsList);
        tableOrder.setRestaurant(restaurant);
        tableOrder.setRestaurantTable(tableRepository.getOne(tableId));

        return tableOrderRepository.save(tableOrder);

    }

    public TableOrder finishOrder(Long id)
    {
        TableOrder tableOrder = this.tableOrderRepository.findOne(id);

        tableOrder.setFinished(true);

        return this.tableOrderRepository.save(tableOrder);
    }

    public OrderItem setItemState(Long id, OrderItemState state)
    {
        OrderItem item = this.orderItemRepository.findById(id);
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        item.setState(state);
        return orderItemRepository.save(item);
    }

    public OrderItem setItemAmount(Long id, Integer amount)
    {
        OrderItem item = this.orderItemRepository.findById(id);



        if(item.getState() != OrderItemState.Waiting)
            return  null;

        item.setAmount(amount);
        return orderItemRepository.save(item);
    }

    public boolean removeItem(Long id)
    {
        OrderItem orderItem = this.orderItemRepository.findById(id);

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        if(orderItem.getState() != OrderItemState.Waiting)
            return false;

        this.orderItemRepository.delete(id);

        if(orderItem.getTableOrder().getOrderItems().isEmpty())
            this.tableOrderRepository.delete(orderItem.getTableOrder().getId());

        return true;
    }
}
