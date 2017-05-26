package com.tim07.domain.DTO;

/**
 * Created by Katarina Cukurov on 22/04/2017.
 */
public class TableDTO {

    private Integer chairNumber;
    private Double top;
    private Double left;
    private Double rotation;

    public TableDTO(){}

    public TableDTO(Integer chairNumber, Double top, Double left, Double rotation) {
        this.chairNumber = chairNumber;
        this.top = top;
        this.left = left;
        this.rotation = rotation;
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
}
