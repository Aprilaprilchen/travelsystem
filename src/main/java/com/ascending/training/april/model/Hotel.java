package com.ascending.training.april.model;

public class Hotel {
    private int id;
    private String name;
    private String location;
    private int price;
    private int comfort_level;
    private int area_id;

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getLocation(){
        return location;
    }
    public int getPrice(){
        return price;
    }
    public int getComfort_level(){
        return comfort_level;
    }
    public int getArea_id(){
        return area_id;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setComfort_level(int comfort_level){
        this.comfort_level = comfort_level;
    }
    public void setArea_id(int area_id){
        this.area_id = area_id;
    }

}
