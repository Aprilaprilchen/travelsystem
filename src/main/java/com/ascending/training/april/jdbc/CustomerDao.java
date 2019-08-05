package com.ascending.training.april.jdbc;

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

    static final String DB_URL = "jdbc:postgresql://localhost:5434/travel_db";
    static final String USER = "admin";
    static final String PASS = "travel123!";

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
            sql = "SELECT * FROM customer";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                Number budget = rs.getInt("budget");
                String gender = rs.getString("gender");
                int area_id = rs.getInt("area_id");

                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setFirst_name(first_name);
                customer.setLast_name(last_name);
                customer.setPassword(password);
                customer.setEmail(email);
                customer.setAge(age);
                customer.setGender(gender);
                customer.setBudget(budget);
                customer.setArea_id(area_id);
                customers.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();

            logger.info("This is an exception");

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
//
//    public static void main(String[] args) {
//        CustomerDao customerDao = new CustomerDao();
//        List<Customer> customers = customerDao.getCustomer();
//
//        for (Customer customer : customers) {
//        System.out.println(customer.getName());
//        }
//    }
}

