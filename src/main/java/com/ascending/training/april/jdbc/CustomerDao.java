package com.ascending.training.april.jdbc;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Customer;
import com.ascending.training.april.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    static final String DB_URL = System.getProperty("database.url");
    static final String USER = System.getProperty("database.user");
    static final String PASS = System.getProperty("database.password");

    public int insertCustomer(Customer customer){
        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql;
            int areaId = 1;

            sql = "INSERT INTO customers(name, first_name, last_name, password, email, age, budget, gender, area_id) values ('"
                    + customer.getName() + "','" + customer.getFirstName() + "','" + customer.getLastName() + "','" +
                    customer.getPassword() + "','" + customer.getEmail() + "','" + customer.getAge() + "','" + customer.getBudget()
                    + "','" + customer.getGender() + "','" + areaId + "')";
            result = stmt.executeUpdate(sql);

        }catch (SQLException e){
            logger.info("This is an exception");
            e.printStackTrace();
        }
        return result;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM customers";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                int budget = rs.getInt("budget");
                String gender = rs.getString("gender");
                int areaId = rs.getInt("area_id");

                Customer customer = new Customer();
//                customer.setId(id);
                customer.setName(name);
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setPassword(password);
                customer.setEmail(email);
                customer.setAge(age);
                customer.setGender(gender);
                customer.setBudget(budget);
//                customer.setAreaId(areaId);
                customers.add(customer);
            }

        } catch (Exception e) {
            logger.info("This is an exception");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        logger.info("This is a message");

        return (customers);
    }

    public Customer getCustomerByName(String customerName){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Customer customer = new Customer();

        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM customers WHERE name = '" + customerName + "'";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                int budget = rs.getInt("budget");
                String gender = rs.getString("gender");
                int areaId = rs.getInt("area_id");

                customer.setName(name);
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setPassword(password);
                customer.setEmail(email);
                customer.setAge(age);
                customer.setGender(gender);
                customer.setBudget(budget);
//                customer.setAreaId(areaId);
            }
        }catch (SQLException e){
            logger.info("This is an exception");
            e.printStackTrace();
        }
        return customer;
    }

    public int deleteCustomerById(Customer customer){
        long customerId = customer.getId();

        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM customers WHERE id = '" + customerId + "'";
            result = stmt.executeUpdate(sql);

        }catch(SQLException e){
            logger.info("This is an exception");
            e.printStackTrace();
        }

        return result;
    }

//    public int deleteCustomerByAreaId(long areaId){
//
//        Connection conn = null;
//        Statement stmt = null;
//        int result = 0;
//
//        try{
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            System.out.println("Creating statement...");
//            stmt = conn.createStatement();
//            String sql;
//            sql = "DELETE FROM customer WHERE area_id = '" + areaId + "'";
//            result = stmt.executeUpdate(sql);
//
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//
//        return result;
//    }

    public int deleteCustomerByName(String customerName){
        Connection conn = null;
        Statement stmt = null;
        int result = 0;

        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM customers WHERE name = '" + customerName + "'";
            result = stmt.executeUpdate(sql);

        }catch(SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
//        CustomerDao customerDao = new CustomerDao();
//        List<Customer> customers = customerDao.getCustomers();
//
//        for (Customer customer : customers) {
//        System.out.println(customer.getName());
//        }
    }
}

