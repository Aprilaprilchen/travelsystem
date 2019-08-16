package com.ascending.training.april.repository;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Customer;

import java.util.List;

public interface CustomerDao {
    boolean save(Customer customer, Area area);
    boolean update(Customer customer);
    int delete(String customerName);
    List<Customer> getCustomers();
    Customer getCustomerByName(String customerName);

}
