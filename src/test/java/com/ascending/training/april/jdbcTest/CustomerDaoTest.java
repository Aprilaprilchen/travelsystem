package com.ascending.training.april.jdbcTest;

import com.ascending.training.april.jdbc.CustomerDao;
import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoTest{
    private CustomerDao customerDao;

    @Before
    public void init(){
       customerDao = new CustomerDao();
    }

    @Test
    public void getCustomerTest(){
        List<Customer> customers = customerDao.getCustomers();

        for (Customer customer : customers){
            System.out.println(customer.getName());
        };

        int ExpectedNumOfCustomer = 4;
        Assert.assertEquals(ExpectedNumOfCustomer, customers.size());

    }

    @Test
    public void deleteCustomerByIdTest(){
        Customer customer = new Customer();
//        customer.setId(1);
        customerDao.deleteCustomerById(customer);
    }

    @Test
    public void deleteCustomerByAreaIdTest(){
        customerDao.deleteCustomerByAreaId(1);
    }

    @After
    public void cleanup() {

        customerDao = null;
    }
}
