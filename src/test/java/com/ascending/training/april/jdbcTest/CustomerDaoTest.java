package com.ascending.training.april.jdbcTest;

import com.ascending.training.april.jdbc.AreaDao;
import com.ascending.training.april.jdbc.CustomerDao;
import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Customer;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoTest{
    private CustomerDao customerDao;
    private Customer customer;
    private String customerName;
    private Area area;
    private AreaDao areaDao;

    @Before
    public void init(){
        customerDao = new CustomerDao();
        customer = new Customer();
        areaDao = new AreaDao();
        area = areaDao.getAreaByName("DC");

        customerName = "Ian";
        customer.setName(customerName);
        customer.setFirstName("Ian");
        customer.setLastName("Somerhalder");
        customer.setEmail("ian@gmail.com");
        customer.setBudget(60000);
        customer.setGender("M");
//        customer.setAreaId(1);
        customerDao.insertCustomer(customer);
    }

    @After
    public void cleanup() {
        if (customer!=null)customerDao.deleteCustomerByName(customerName);
        customerDao = null;
    }

    @Test
    public void insertCustomerTest(){
        Customer customer2 = customerDao.getCustomerByName(customerName);
        Assert.assertNotNull(customer2);
    }

    @Test
    public void getCustomerTest(){
        List<Customer> customers = customerDao.getCustomers();

        for (Customer customer : customers){
            System.out.println(customer.getName());
        }
        int ExpectedNumOfCustomer = 5;
        Assert.assertEquals(ExpectedNumOfCustomer, customers.size());
    }

    @Test
    public void deleteCustomerByNameTest(){
        int a = customerDao.deleteCustomerByName(customerName);
        Assert.assertEquals(1, a);
    }

//    @Test
//    public void deleteCustomerByAreaIdTest(){
//        customerDao.deleteCustomerByAreaId(1);
//    }
}