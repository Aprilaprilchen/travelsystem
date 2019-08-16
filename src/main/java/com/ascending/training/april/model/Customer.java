package com.ascending.training.april.model;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "budget")
    private int budget;

    @Column(name = "gender")
    private String gender;

//    @Column(name = "area_id")
//    private long areaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    public Area getArea(){
        return area;
    }
    public void setArea(Area area){
        this.area = area;
    }

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
    public int getBudget(){
        return budget;
    }
    public String getGender(){
        return gender;
    }
//    public long getAreaId(){
//        return areaId;
//    }

//    public void setId(long id) {
//        this.id = id;
//    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public void setBudget(int budget) {
        this.budget = budget;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
//    public void setAreaId(long areaId) {
//        this.areaId = areaId;
//    }
}
