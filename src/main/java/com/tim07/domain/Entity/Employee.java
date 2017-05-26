package com.tim07.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tim07.domain.Enumeration.UserType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by freez on 28-Apr-17.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee extends User implements Serializable{

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("employee")
    private List<DaySchedule> daySchedules;

    @Column(columnDefinition = "boolean default true",nullable = false)
    private Boolean firstLogin;

    public Employee() {
        this.daySchedules = new ArrayList<>();
        AddSchedules();
        this.firstLogin = true;
    }

    public Employee(String name, String lastname, String username, String password, UserType type) {
        super(name, lastname, username, password, type);
        this.daySchedules = new ArrayList<>();
        AddSchedules();
        this.firstLogin = true;
    }

    public List<DaySchedule> getDaySchedules() {
        return daySchedules;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    //Test data. Delete this method when setting employee day schedule is implemented.
    public void AddSchedules()
    {
        for(int i = 0; i < 5; i++)
            this.daySchedules.add(new DaySchedule(this,(i+1) + "/5/2017", "07:30","15:30"));

    }
}
