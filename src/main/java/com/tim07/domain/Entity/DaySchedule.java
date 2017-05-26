package com.tim07.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by freez on 24-Apr-17.
 */
@Entity
public class DaySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @Column(nullable = false)
    @NotNull
    private String day;

    @Column(nullable = false)
    @NotNull
    private String start;

    @Column(nullable = false)
    @NotNull
    private String end;

    public DaySchedule() {}

    public DaySchedule(Employee employee, String day, String start, String end) {
        this.employee = employee;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getDay() {
        return day;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
