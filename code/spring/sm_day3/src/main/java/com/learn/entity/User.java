package com.learn.entity;

import java.util.Date;

/**
 * @author Kelly
 * @create 2020-06-10 07:50
 */
public class User {
    private String id;
    private String name;
    private int age;
    private Date bir;

    public User() {
    }

    public User(String id, String name, int age, Date bir) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bir = bir;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBir() {
        return bir;
    }

    public void setBir(Date bir) {
        this.bir = bir;
    }
}
