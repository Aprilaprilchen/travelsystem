package com.ascending.training.april.controller;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Customer;
import com.ascending.training.april.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/cus", "/customers"})
public class CustomerController {
    private final Logger loggger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/{customerName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomer(@PathVariable String customerName){
        return customerService.getCustomerByName(customerName);
    }

    @RequestMapping(value = "/{areaName}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createCustomer(@RequestBody Customer customer, @PathVariable String areaName){
        //loggger.debug(customer.getName());
        //if (customer.getArea() == null) loggger.debug("are is null");
        loggger.debug("customer:" + customer.toString() + ", area name: " + areaName);
        String msg = "This customer was created";
        boolean isSuccess = customerService.save(customer, areaName);

        if(!isSuccess){
            msg = "This customer was not created";
        }

        return msg;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateCustomer(@RequestBody Customer customer){
        loggger.debug("customer:"+ customer.toString());
        String msg = "This customer was updated";
        boolean isSuccess = customerService.update(customer);

        if(!isSuccess){
            msg = "This customer was not updated";
        }
        return msg;
    }

    @RequestMapping(value = "/{customerName}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCustomer(@PathVariable String customerName){
        loggger.debug("customer:" + customerName.toString());
        String msg = "This customer was deleted";
        int isSuccess = customerService.delete(customerName);

        if(isSuccess == 0){
            msg = "This customer was not deleted";
        }

        return msg;
    }
}
