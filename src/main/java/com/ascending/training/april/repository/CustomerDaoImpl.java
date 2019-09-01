package com.ascending.training.april.repository;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Customer;
import com.ascending.training.april.util.HibernateUtil;
import com.github.fluent.hibernate.H;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired AreaDao areaDao;

    @Override
    public boolean save(Customer customer, Area area){
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            customer.setArea(area);
            session.save(customer);

            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The area %s was inserted into the table", customer.toString()));

        return isSuccess;
    }

    @Override
    public boolean save(Customer customer, String areaName){
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Area area = areaDao.getAreaByName(areaName);
            customer.setArea(area);
            session.save(customer);

            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The area %s was inserted into the table", customer.toString()));

        return isSuccess;
    }

    @Override
    public boolean update(Customer customer) {
       Transaction transaction = null;
       boolean isSuccess = true;

       try(Session session =HibernateUtil.getSessionFactory().openSession()){
           transaction = session.beginTransaction();
           session.saveOrUpdate(customer);
           transaction.commit();
       }catch (Exception e){
           isSuccess = false;
           if (transaction != null) transaction.rollback();
           logger.error(e.getMessage());
       }
       if(isSuccess)logger.debug(String.format("The customer %s was updated", customer.toString()));

       return isSuccess;
    }

    @Override
    public int delete(String customerName) {
        String hql = "Delete Customer where name =:customerName";
        int deleteCount = 0;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Customer> query = session.createQuery(hql);
//            query.setParameter("id", customerId);
            query.setParameter("customerName", customerName);

            transaction = session.beginTransaction();
            deleteCount = query.executeUpdate();
            transaction.commit();

        }catch(Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());

        }

        return deleteCount;
    }

    @Ignore
    @Override
    public List<Customer> getCustomers() {
        String hql = "FROM Customer";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Customer>query = session.createQuery(hql);
            return query.list();
        }
    }
//
    @Override
    public Customer getCustomerByName(String customerName) {
        if(customerName == null) return null;
        String hql = "FROM Customer as customer where lower(customer.name) =:name";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Customer>query = session.createQuery(hql);
            query.setParameter("name", customerName.toLowerCase());

            Customer customer = query.uniqueResult();
            logger.debug(customer.toString());

            return customer;
        }
    }
}
