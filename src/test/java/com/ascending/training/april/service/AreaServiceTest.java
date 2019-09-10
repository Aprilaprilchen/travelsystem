package com.ascending.training.april.service;

import com.ascending.training.april.init.AppInitializer;
import com.ascending.training.april.model.Area;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class AreaServiceTest {

    @Autowired Logger logger;
    @Autowired
    private AreaService areaService;
    private Area a;

    //private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void init(){
//        areaDaoImpl = new AreaDaoImpl();
        a = new Area();
        a.setName("CCC");
        a.setConsumptionLevel(4);
        a.setDescription("Crowded of Customers");
        a.setLocation("Downtown");

        boolean result =  areaService.save(a);
        logger.info("111111");
        System.out.println(result);
    }

    @Test
    public void saveAreaTest(){
        List<Area> areas = areaService.getAreas();
        String newName = "CCC";
        String newNameIsNewName = a.getName();
        Assert.assertEquals(newName, newNameIsNewName);
    }

    @Test
    public void updateAreaNameTest(){
        int result = areaService.updateAreaName(28, "FFF");
        System.out.println(result);
    }

    @Test
    public void deleteAreaByNameAndLocationTest(){
        areaService.delete("EEE", "Urban");
        String deletedName = a.getName();
        Assert.assertNotNull(deletedName);
    }

    @Test
    public void getAreasTest() {
        List<Area> areas = areaService.getAreas();
        System.out.println(areas);
    }

    @Test
    public void getAreaByNameTest(){
        Area area = areaService.getAreaByName("VA");
        System.out.println(area);
        Assert.assertNotNull(area);
    }

    @After
    public void cleanup(){
        try{
            System.out.println("Enter Cleanup...");
            Area area = areaService.getAreaByName("CCC" );
            if(area != null){
                areaService.delete("CCC","Downtown");
                System.out.println("The record has been deleted!");
            }
            else System.out.println("Area is null");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        areaService = null;
    }
}
