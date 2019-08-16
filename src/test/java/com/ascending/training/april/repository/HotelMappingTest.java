package com.ascending.training.april.repository;

import com.ascending.training.april.model.Customer;
import com.ascending.training.april.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HotelMappingTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void mappingTest(){
        String hql = "FROM Customer";
        List<Customer> customers = null;

        try (
                Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            customers = query.list();
//                session.close();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

        Assert.assertNotNull(customers);
    }
}
