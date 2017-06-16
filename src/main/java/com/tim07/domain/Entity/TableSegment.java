package com.tim07.domain.Entity;

import com.tim07.domain.Enumeration.Segment;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by brzak on 16.6.17..
 */
@Entity
public class TableSegment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Segment segment;

    @ManyToOne
    private Waiter waiter;

    public TableSegment(){}

    public TableSegment(Segment segment, Waiter waiter) {
        this.segment = segment;
        this.waiter = waiter;
    }

    public Long getId() {
        return id;
    }


    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }
}
