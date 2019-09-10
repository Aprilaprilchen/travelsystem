package com.ascending.training.april.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "areas")
public class Area {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "consumption_level")
    private int consumptionLevel;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

@OneToMany(mappedBy = "area", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Customer> customers;

@OneToMany(mappedBy = "area", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Hotel> hotels;

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Hotel> getHotels(){
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels){
        this.hotels = hotels;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getConsumptionLevel(){
        return consumptionLevel;
    }

    public String getLocation(){
        return location;
    }

    public String getDescription(){
        return description;
    }


//    public void setId(long id){
//        this.id = id;
//    }

    public void setName(String name){

        this.name = name;
    }
    public void setConsumptionLevel(int consumptionLevel){

        this.consumptionLevel = consumptionLevel;
    }
    public void setLocation(String location){

        this.location = location;
    }
    public void setDescription(String description){

        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return id == area.id &&
                name.equals(area.name) &&
                location.equals(area.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location);
    }

    public void setCustomers(long customerId, String customerEmail) {

    }
}
