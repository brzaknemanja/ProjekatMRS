package com.tim07.domain.DTO;

import com.tim07.domain.Enumeration.ItemType;

/**
 * Created by brzak on 18.6.17..
 */
public class OrderItemDTO {

    private String name;

    private Double price;

    private ItemType type;

    private Integer amount;

    private boolean finished;

    public OrderItemDTO() {}

    public OrderItemDTO(String name, Double price, ItemType type, Integer amount) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.amount = amount;
        this.finished = false;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
