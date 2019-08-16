package com.ascending.training.april.repository;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AreaMappingTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void mappingTest(){
            String hql = "FROM Area";
            List<Area> areas = null;

        try (
                Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Area> query = session.createQuery(hql);
            areas = query.list();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }

        Assert.assertNotNull(areas);
    }
}

