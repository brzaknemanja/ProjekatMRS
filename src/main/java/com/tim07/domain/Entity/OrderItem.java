package com.tim07.domain.Entity;

import com.tim07.domain.Enumeration.ItemType;
import com.tim07.domain.Enumeration.OrderItemState;

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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderItemState state;

    @ManyToOne
    private TableOrder tableOrder;

    @Version
    @Column(name = "VERSION")
    private Integer version;


    public OrderItem() {}

    public OrderItem(String name, Double price, ItemType type, Integer amount, TableOrder tableOrder) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.state = OrderItemState.Waiting;
        this.amount = amount;
        this.tableOrder = tableOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public OrderItemState getState() {
        return state;
    }

    public void setState(OrderItemState state) {
        this.state = state;
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
