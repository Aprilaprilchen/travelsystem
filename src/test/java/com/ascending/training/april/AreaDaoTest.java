package com.ascending.training.april;

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

        int expectedNumOfArea = 5;
        Assert.assertEquals(expectedNumOfArea, areas.size());

    }

    @After
    public void cleanup() {
        areaDao = null;
    }
}
