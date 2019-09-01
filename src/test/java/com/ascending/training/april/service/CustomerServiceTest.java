package com.ascending.training.april.service;

import com.ascending.training.april.init.AppInitializer;
import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Customer;
import com.ascending.training.april.repository.AreaDaoImpl;
import com.ascending.training.april.repository.CustomerDaoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class CustomerServiceTest {
    private Customer c = new Customer();
    private Area a = new Area();

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AreaService areaService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
//        customerDaoImpl = new CustomerDaoImpl();
        a = areaService.getAreaByName("VA");

        c = new Customer();
        c.setName("Taylor Swift");
        c.setFirstName("Taylor");
        c.setLastName("Swift");
        c.setGender("F");
        c.setAge(24);
        c.setEmail("taylor@emai.com");
        c.setPassword("123789");
        c.setBudget(100000);
//        c.setArea(a);
//        c.setAreaId(9);

        boolean result = customerService.save(c, a);
        System.out.println(result);
    }

    @After
    public void cleanup(){
        try{
            System.out.println("Enter Cleanup...");
            Customer customer = customerService.getCustomerByName("Taylor Swift" );
            if(customer != null){
                customerService.delete("Taylor Swift");
                System.out.println("The record has been deleted!");
            }
            else System.out.println("customer is null");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        customerService = null;
    }



    //    @Ignore
    @Test
    public void saveCustomerTest(){
//        List<Customer> customers = customerDaoImpl.getCustomers();
        String newName = "Taylor Swift";
        String newNameIsNewName = c.getName();
        Assert.assertEquals(newName, newNameIsNewName);
    }

    //    @Ignore
    @Test
    public void updateCustomerTest(){
        c.setPassword("999");
        boolean result = customerService.update(c);
        Customer d = customerService.getCustomerByName("rhang");
        d.setBudget(200000);
        boolean result2 = customerService.update(d);
        int expectedBudget = 200000;
        int realBudget = d.getBudget();
        Assert.assertEquals(expectedBudget, realBudget);
        System.out.println(result);
        System.out.println(result2);
    }

    //    @Ignore
    @Test
    public void deleteCustomerTest(){
        int count = customerService.delete( "Taylor Swift");
//        Customer e = customerDaoImpl.getCustomerByName("Taylor Swift");
//        String deletedName = e.getName();
        int expectedOfNum = 1;
        Assert.assertEquals(expectedOfNum, count);
    }

    //    @Ignore
    @Test
    public void getCustomersTest(){
        List<Customer> customers = customerService.getCustomers();
        System.out.println(customers);
    }

    @Test
    public void getCustomersByName(){
        Customer customer1 = customerService.getCustomerByName("rhang");
        Assert.assertNotNull(customer1);
    }
}
