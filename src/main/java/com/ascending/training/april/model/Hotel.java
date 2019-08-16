package com.ascending.training.april.model;

import javax.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "price")
    private int price;

    @Column(name = "comfortLevel")
    private int comfortLevel;

    //@Column(name = "area_id")
    //private long areaId;

    public long getId(){
        return id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    public Area getArea(){
        return area;
    }

    public void setArea(Area area){
        this.area = area;
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
    public int getComfortLevel(){
        return comfortLevel;
    }
//    public long getAreaId(){
//        return areaId;
//    }

//    public void setId(long id){
//        this.id = id;
//    }
    public void setName(String name){
        this.name = name;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setComfortLevel(int comfort_level){
        this.comfortLevel = comfortLevel;
    }
//    public void setAreaId(long areaId){ this.areaId = areaId;
//    }

}
