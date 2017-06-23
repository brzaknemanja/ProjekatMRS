package com.tim07.domain.DTO;

import com.tim07.domain.Enumeration.ItemType;
import com.tim07.domain.Enumeration.OrderItemState;

/**
 * Created by brzak on 18.6.17..
 */
public class OrderItemDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private ItemType type;

    private Integer amount;

    private OrderItemState state;

    public OrderItemDTO() {}

    public OrderItemDTO(Long id,String name, String description, Double price, ItemType type, Integer amount, OrderItemState state) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.amount = amount;
        this.state = state;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public OrderItemState getState() {
        return state;
    }

    public void setState(OrderItemState state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
