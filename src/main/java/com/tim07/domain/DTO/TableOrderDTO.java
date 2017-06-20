package com.tim07.domain.DTO;

import java.util.List;

/**
 * Created by brzak on 18.6.17..
 */
public class TableOrderDTO {

    private List<OrderItemDTO> orderItems;
    private Long tableId;

    public TableOrderDTO() {}

    public TableOrderDTO(List<OrderItemDTO> orderItems) {
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
}
