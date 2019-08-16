package com.ascending.training.april.inhibernateTest;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Customer;
import com.ascending.training.april.repository.AreaDaoImpl;
import com.ascending.training.april.repository.CustomerDao;
import com.ascending.training.april.repository.CustomerDaoImpl;
import org.junit.*;

import java.util.List;

public class CustomerDaoImplTest{
    private CustomerDaoImpl customerDaoImpl;
    private AreaDaoImpl areaDaoImpl;
    private Customer c;
    private Area a;

    @Before
    public void init(){
        customerDaoImpl = new CustomerDaoImpl();
        areaDaoImpl = new AreaDaoImpl();
        a = areaDaoImpl.getAreaByName("FFF");

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

        boolean result = customerDaoImpl.save(c, a);
        System.out.println(result);
    }

    @After
    public void cleanup(){
        try{
            System.out.println("Enter Cleanup...");
            Customer customer = customerDaoImpl.getCustomerByName("Taylor Swift" );
            if(customer != null){
                customerDaoImpl.delete("Taylor Swift");
                System.out.println("The record has been deleted!");
            }
            else System.out.println("customer is null");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        customerDaoImpl = null;
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
        boolean result = customerDaoImpl.update(c);
        Customer d = customerDaoImpl.getCustomerByName("rhang");
        d.setBudget(200000);
        boolean result2 = customerDaoImpl.update(d);
        int expectedBudget = 200000;
        int realBudget = d.getBudget();
        Assert.assertEquals(expectedBudget, realBudget);
        System.out.println(result);
        System.out.println(result2);
    }

//    @Ignore
    @Test
    public void deleteCustomerTest(){
        int count = customerDaoImpl.delete( "Taylor Swift");
//        Customer e = customerDaoImpl.getCustomerByName("Taylor Swift");
//        String deletedName = e.getName();
        int expectedOfNum = 1;
        Assert.assertEquals(expectedOfNum, count);
    }

//    @Ignore
    @Test
    public void getCustomersTest(){
        List<Customer> customers = customerDaoImpl.getCustomers();
        System.out.println(customers);
    }

    @Test
    public void getCustomersByName(){
        Customer customer1 = customerDaoImpl.getCustomerByName("rhang");
        Assert.assertNotNull(customer1);
    }
}
