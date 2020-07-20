package com.example.RestService.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Entity
public class Person {

    @Id
    private int id;
    private String carNum;
    private Boolean registered;

    protected Person(){}

     public Person(int id,
                  String carNum) {
        this.id = id;
        this.carNum = carNum;
        this.registered = false;
    }

    public Person(int id,
                  String carNum,
                  Boolean registered) {
        this.id = id;
        this.carNum = carNum;
        this.registered = registered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carName) {
        this.carNum = carName;
    }

    public Boolean getRegistered() {
        return registered;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }
}
