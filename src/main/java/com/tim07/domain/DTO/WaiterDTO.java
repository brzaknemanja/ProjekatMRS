package com.tim07.domain.DTO;

import com.tim07.domain.Entity.DaySchedule;
import com.tim07.domain.Enumeration.UserType;

import java.util.List;

/**
 * Created by Ivana Zeljkovic on 17-Apr-17.
 */
public class WaiterDTO {
    private String name;
    private String lastname;
    private String username;
    private UserType type;
    private String dateOfBirth;
    private Integer dressSize;
    private Integer shoeSize;
    private List<DaySchedule> daySchedules;
    private Boolean firstLogin;

    public WaiterDTO() { }

    public WaiterDTO(String name, String lastname, String username, UserType type, String dateOfBirth, Integer dressSize,
                     Integer shoeSize, List<DaySchedule> daySchedules, Boolean firstLogin) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.type = type;
        this.dateOfBirth = dateOfBirth;
        this.dressSize = dressSize;
        this.shoeSize = shoeSize;
        this.daySchedules = daySchedules;
        this.firstLogin = firstLogin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getDressSize() {
        return dressSize;
    }

    public void setDressSize(Integer dressSize) {
        this.dressSize = dressSize;
    }

    public Integer getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(Integer shoeSize) {
        this.shoeSize = shoeSize;
    }

    public List<DaySchedule> getDaySchedules() {
        return daySchedules;
    }

    public void setDaySchedules(List<DaySchedule> daySchedules) {
        this.daySchedules = daySchedules;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }
}
