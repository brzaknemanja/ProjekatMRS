package com.tim07.domain.DTO;

import com.tim07.domain.Entity.OrderItem;
import com.tim07.domain.Enumeration.OrderItemState;

import java.util.List;

/**
 * Created by brzak on 18.6.17..
 */
public class TableOrderDTO {

    private Long id;
    private List<OrderItemDTO> orderItems;
    private Long tableId;
    private String tableName;
    private boolean ready;
    private boolean finished;

    public TableOrderDTO() {}

    public TableOrderDTO(Long id,List<OrderItemDTO> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isReady() {
        for(OrderItemDTO item: orderItems)
        {
            if(item.getState() != OrderItemState.Finished)
                return false;
        }

        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
