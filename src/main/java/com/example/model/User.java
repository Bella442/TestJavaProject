package com.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class User {
    private int id;
    private String name;
    private String position;
    private String role;
    private int salary;

    public User() {
    }
//    public User(int id, String name, String position, String role, int salary) {
//        this.id = id;
//        this.name = name;
//        this.position = position;
//        this.role = role;
//        this.salary = salary;
//    }

}
