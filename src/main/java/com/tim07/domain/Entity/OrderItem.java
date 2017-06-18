package com.tim07.domain.Entity;

import com.tim07.domain.Enumeration.ItemType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by brzak on 17.6.17..
 */
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private Double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Column(nullable = false)
    @NotNull
    private Integer amount;

    @Column(columnDefinition = "boolean default false",nullable = false)
    private boolean finished;

    @ManyToOne
    private TableOrder tableOrder;


    public OrderItem() {}

    public OrderItem(String name, Double price, ItemType type, Integer amount, TableOrder tableOrder) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.finished = false;
        this.amount = amount;
        this.tableOrder = tableOrder;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public TableOrder getTableOrder() {
        return tableOrder;
    }

    public void setTableOrder(TableOrder tableOrder) {
        this.tableOrder = tableOrder;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
