package com.example.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private int id;
    private String name;
    private String position;
    private String role;
    private int salary;

    public User() {
    }

    ;
//    public User(int id, String name, String position, String role, int salary) {
//        this.id = id;
//        this.name = name;
//        this.position = position;
//        this.role = role;
//        this.salary = salary;
//    }

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
