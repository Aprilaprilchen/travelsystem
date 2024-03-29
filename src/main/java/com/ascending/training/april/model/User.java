package com.ascending.training.april.model;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//public class Users {
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "first_name")
//    private String firstName;
//
//    @Column(name = "last_name")
//    private String lastName;
//
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "email")
//    private String email;
//
//    public long getId(){
//        return id;
//    }
//    public String getName(){
//        return name;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public String getPassword(){
//        return password;
//    }
//    public String getEmail(){
//        return email;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//}

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    //@SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
    //@GeneratedValue(strategy = SEQUENCE, generator = "user_id_generator")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "secret_key")
    private String secretKey;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    //@ManyToMany(mappedBy = "users", cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private List<Role> roles;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password.trim());
    }
    public String getSecretKey() {
        return secretKey;
    }
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                name.equals(user.name) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                email.equals(user.email);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, secretKey, firstName, lastName, email);
    }
    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;
        try {
            str = objectMapper.writeValueAsString(this);
        }
        catch(JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        return str;
    }


    public int hashcode(){
        Objects.hash(name, email);

        final int prime = 17;
        int result = 1;
        result = prime*result + name.hashCode();
        result = prime*result + email.hashCode();

        return result;
    }

//    public boolean equals(Object u){
//        if (this == u)return true;
//        if (this.getClass() != u.getClass()) return false;
//
//        User other = (User) u;
//        if (!email.equals(other.email)) return false;
//        if (!name.equals(other.name)) return false;
//
//        return true;
//    }
}
