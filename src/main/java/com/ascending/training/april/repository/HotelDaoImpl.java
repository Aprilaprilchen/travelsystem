package com.ascending.training.april.repository;

import com.ascending.training.april.jdbc.AreaDao;
import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Hotel;
import com.ascending.training.april.util.HibernateUtil;
import com.github.fluent.hibernate.H;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class HotelDaoImpl implements HotelDao{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Hotel hotel, Area area) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            hotel.setArea(area);
            session.save(hotel);
            transaction.commit();
        }catch(Exception e){
            isSuccess = false;
            if(transaction != null)
            {transaction.rollback();
            logger.error(e.getMessage());
            }
        }

        if(isSuccess) {
            logger.debug(String.format("The hotel %s was inserted into table", hotel.toString()));
        }

        return isSuccess;
    }

    @Override
    public boolean save(Hotel hotel, String areaName){
        Transaction transaction = null;
        boolean isSuccess = true;
        AreaDaoImpl areaDaoImpl = new AreaDaoImpl();

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            Area area = areaDaoImpl.getAreaByName(areaName);
            hotel.setArea(area);
            session.save(hotel);
            transaction.commit();
        }catch (Exception e){
            isSuccess = false;
            if(transaction != null){
                transaction.rollback();
                logger.debug(e.getMessage());
            }
        }

        if(isSuccess){
            logger.debug(String.format("The hotel %s was inserted into table", hotel.toString()));
        }

        return isSuccess;
    }

    @Override
    public boolean update(Hotel hotel) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.update(hotel);
            transaction.commit();
        }catch (Exception e){
            isSuccess = false;
            if(transaction != null) {
                transaction.rollback();
                logger.error(e.getMessage());
            }
        }
        if (isSuccess = true){
            logger.debug(String.format("This hotel %s is already updated", hotel.toString()));
        }
        return isSuccess;
    }

    @Override
    public int delete(String hotelName) {
        String hql = "Delete Hotel where name =:hotelName";
        Transaction transaction = null;
        int deleteCount = 0;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Query<Hotel> query = session.createQuery(hql);
            query.setParameter("hotelName", hotelName);

            transaction = session.beginTransaction();
            deleteCount = query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            deleteCount = 0;
            if(transaction != null){
                transaction.rollback();
                logger.error(e.getMessage());
            }

            if(deleteCount != 0){
                logger.debug(String.format("This hotel %s hotelName has already been deleted", hotelName.toString()));
            }
        }

        return deleteCount;
    }

    @Override
    public List<Hotel> getHotels(){
        String hql = "From Hotel";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Hotel> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Hotel getHotelByName(String hotelName) {
        if (hotelName == null){
            return null;
        }
        String hql = "FROM Hotel as hotel where lower(hotel.name) =:name";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Hotel> query = session.createQuery(hql);
            query.setParameter("name", hotelName.toLowerCase());

            Hotel hotel = query.uniqueResult();
            logger.debug(hotel.toString());

            return hotel;
        }
    }
}
