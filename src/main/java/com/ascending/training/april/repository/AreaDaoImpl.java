package com.ascending.training.april.repository;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaDaoImpl implements AreaDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Area area) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(area);
            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The area %s was inserted into the table", area.toString()));

        return isSuccess;
    }

    @Override
    public int updateAreaName(long areaId, String areaName) {
        String hql = "UPDATE Area as area set area.name = :name where area.id = :id";
        int updatedCount = 0;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Area> query = session.createQuery(hql);
            query.setParameter("id", areaId);
            query.setParameter("name", areaName);

            transaction = session.beginTransaction();
            updatedCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The area %s was updated, total updated record(s): %d", areaId, updatedCount));

        return updatedCount;
    }

//    public int updateArea(Area area){
//        String hql = "UPDATE Area as area set area.id =:id";
//        int updateCount = 0;
//        Transaction transaction = null;
//
//        try(Session session = HibernateUtil.getSessionFactory().openSession()){
//            Query<Area> query = session.createQuery(hql);
//            query.setParameter("id", areaId);
//
//            transaction = session.beginTransaction();
//            session.saveOrUpdate(area);
//            updateCount = query.executeUpdate();
//            transaction.commit();
//            updateCount ++;
//        }
//        catch (Exception e){
//            if (transaction !=null)transaction.rollback();
//            logger.error(e.getMessage());
//        }
//
//        logger.debug(String.format("The area %s was updated, total updated record(s): %d", area.getupdateCount));
//
//        return updateCount;
//    }

    public boolean update(Area area) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(area);
            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The area %s was updated", area.toString()));

        return isSuccess;
    }



    @Override
    public int delete(String areaName, String areaLocation) {
//        place holer 是一个变化的值 自动识别
        String hql = "DELETE Area where name = :areaName1 and location = :areaLocation1";
        int deletedCount = 0;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Area> query = session.createQuery(hql);
//            name同上，表示要替换掉areaName1
            query.setParameter("areaName1", areaName);
            query.setParameter("areaLocation1", areaLocation);

            transaction = session.beginTransaction();
            deletedCount = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        return deletedCount;
//        return deletedCount >= 1 ? true : false;
    }

    @Override
    public List<Area> getAreas() {
        String hql = "FROM Area";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Area>query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Area getAreaByName (String areaName){
        if (areaName == null) return null;
//        转换成小写
        String hql = "FROM Area as area left join fetch area.customers where lower(area.name) = :name";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Area> query = session.createQuery(hql);
            query.setParameter("name", areaName.toLowerCase());

            Area area = query.uniqueResult();
            logger.debug(area.toString());

            return area;
        }
    }

    public Area getAreaById (long areaId){
        if (areaId < 0) return null;
        String hql = "FROM Area as area where area.id = :id1";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Area> query = session.createQuery(hql);
            query.setParameter("id1", areaId);

            Area area = query.uniqueResult();
            logger.debug(area.toString());

            return area;
        }
    }

}
