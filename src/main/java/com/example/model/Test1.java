package com.example.model;

/**
 * Created by sjf on 17-2-26.
 */
public class Test1 {

    private Integer id;
    private String name;
    private String test;


    public Test1(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Test1() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
