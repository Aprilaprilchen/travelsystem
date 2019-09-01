package com.ascending.training.april.service;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Customer;
import com.ascending.training.april.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public boolean save(Customer customer, Area area){
        return customerDao.save(customer, area);
    }
    public boolean save(Customer customer, String areaName){
        return customerDao.save(customer, areaName);
    }
    public boolean update(Customer customer){
        return customerDao.update(customer);
    }
    public int delete(String customerName){
        return customerDao.delete(customerName);
    }
    public List<Customer> getCustomers(){
        return customerDao.getCustomers();
    }
    public Customer getCustomerByName(String customerName){
        return customerDao.getCustomerByName(customerName);
    }
}
