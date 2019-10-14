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
    private Area ff;
    private Long areaId;
    private String areaName = "FairFax";

    @Before
    public void init(){
        areaDao = new AreaDao();
        ff = new Area();
        ff.setName(areaName);
        ff.setLocation("Falls Church");
        ff.setDescription("Urban");
        ff.setConsumptionLevel(4);
        areaDao.insertAreas(ff);
    }

    @After
    public void cleanup() {
        if (ff!=null) areaDao.deleteAreaByName(areaName);
        areaDao = null;
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
    public void getAreaByNameTest(){
        Area area = areaDao.getAreaByName("DC");
        Assert.assertNotNull(area);
    }

    @Test
    public void insertAreaTest(){
        Area area = areaDao.getAreaByName(areaName);
        Assert.assertNotNull(area);
    }

    @Test
    public void updateAreaTest(){
        Area area = areaDao.getAreaByName(areaName);
        area.setName("DDD");
        areaDao.updateArea(area);
        Area area2 = areaDao.getAreaByName("DDD");
        Assert.assertNotNull(area2);
    }

    @Test
    public void deleteAreaByNameTest(){
        areaDao.deleteAreaByName(areaName);
        List<Area> areas = areaDao.getAreas();
        Assert.assertEquals(5, areas.size());
    }
}
