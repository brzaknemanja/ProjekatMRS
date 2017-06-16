package com.tim07.domain.DTO;

import com.tim07.domain.Enumeration.Segment;

/**
 * Created by Katarina Cukurov on 22/04/2017.
 */
public class TableDTO {

    private String name;
    private Integer chairNumber;
    private Double top;
    private Double left;
    private Double rotation;
    private Segment segment;


    public TableDTO(){}

    public TableDTO(String name,Integer chairNumber, Double top, Double left, Double rotation,
    Segment segment) {
        this.name = name;
        this.chairNumber = chairNumber;
        this.top = top;
        this.left = left;
        this.rotation = rotation;
        this.segment = segment;
    }

    public Integer getChairNumber() {
        return chairNumber;
    }

    public void setChairNumber(Integer chairNumber) {
        this.chairNumber = chairNumber;
    }

    public Double getTop() {
        return top;
    }

    public void setTop(Double top) {
        this.top = top;
    }

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public Double getRotation() {
        return rotation;
    }

    public void setRotation(Double rotation) {
        this.rotation = rotation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }
}
