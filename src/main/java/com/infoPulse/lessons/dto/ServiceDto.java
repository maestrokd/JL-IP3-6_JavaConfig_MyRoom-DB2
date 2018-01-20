package com.infoPulse.lessons.dto;

import com.infoPulse.lessons.classesForTable.Service;

public class ServiceDto {

    // Fields

    private int id;
    private String name;
    private float payroll;


    // Constructors

    public ServiceDto() {
    }

    public ServiceDto(Service service) {
        this.id = service.getId();
        this.name = service.getName();
        this.payroll = service.getPayroll();
    }


    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPayroll() {
        return payroll;
    }

    public void setPayroll(float payroll) {
        this.payroll = payroll;
    }
}
