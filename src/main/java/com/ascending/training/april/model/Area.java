package com.ascending.training.april.model;

public class Area {
    private int id;
    private String name;
    private int comsumption_level;
    private String location;
    private String description;

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getComsumption_level(){
        return comsumption_level;
    }
    public String getLocation(){
        return location;
    }
    public String getDescription(){
        return description;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setComsumption_level(int comsumption_level){
        this.comsumption_level = comsumption_level;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setDescription(String description){
        this.description = description;
    }

}
