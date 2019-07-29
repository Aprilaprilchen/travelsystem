package com.ascending.training.april.model;

import java.awt.font.NumericShaper;

public class Customer {

    private int id;
    private String name;
    private String first_name;
    private String last_name;
    private String password;
    private String email;
    private int age;
    private Number budget;
    private char gender;
    private int area_id;

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getFirst_name(){
        return first_name;
    }
    public String getLast_name(){
        return last_name;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }
    public int getAge(){
        return age;
    }
    public Number getBudget(){
        return budget;
    }
    public char getGender(){
        return gender;
    }
    public int getArea_id(){
        return area_id;
    }

    public void setId(int age) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setBudget(Number budget) {
        this.budget = budget;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }
}
