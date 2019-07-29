package com.ascending.training.april;

import com.ascending.training.april.jdbc.AreaDao;
import com.ascending.training.april.model.Area;
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
        AreaDao areaDao = new AreaDao();
        List<Area> areas = areaDao.getArea();

        for (Area area : areas) {
            System.out.println(area.getName());
        }
    }
    public void getArea(){
        List<Area> areas = areaDao.getArea();
        int expectedNumOfArea = 5;

        for (Area area : areas){
            System.out.println(area);
        }

        Assert.assertEquals(expectedNumOfArea, areas.size());
    }
}
