package com.ascending.training.april.jdbcTest;

import com.ascending.training.april.jdbc.AreaDao;
import com.ascending.training.april.model.Area;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class AreaDaoTest {
    private AreaDao areaDao;

    @Before
    public void init(){
        areaDao = new AreaDao();
    }

    @Test
    public void getAreaTest(){
        List<Area> areas = areaDao.getAreas();

        for (Area area : areas) {
            System.out.println(area.getName());
        }

        int expectedNumOfArea = 6;
        Assert.assertEquals(expectedNumOfArea, areas.size());

    }

    @Test
    public void insertAreaTest(){
        Area va = new Area();
        va.setLocation("Crystal City");
        va.setDescription("Downtown");
        va.setConsumptionLevel(4);
        va.setName("VA");
        //va.setId(100);
        areaDao.insertAreas(va);
    }

    @Test
    public void updateAreaTest(){
        Area area2 = new Area();
        area2.setName("DDD");
//        area2.setId(2);
        areaDao.updateArea(area2);
    }

    @Test
    public void deleteAreaByIdTest(){
        areaDao.deleteAreaById(1);
    }

    @After
    public void cleanup() {
        areaDao = null;
    }
}
