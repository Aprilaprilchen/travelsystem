package com.ascending.training.april.inhibernateTest;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Customer;
import com.ascending.training.april.repository.AreaDaoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AreaDaoImplTest {
    private AreaDaoImpl areaDaoImpl;
    private Area a;

    @Before
    public void init(){
        areaDaoImpl = new AreaDaoImpl();
        a = new Area();
        a.setName("CCC");
        a.setConsumptionLevel(4);
        a.setDescription("Crowded of Customers");
        a.setLocation("Downtown");

        boolean result =  areaDaoImpl.save(a);
        System.out.println(result);
    }

    @Test
    public void saveAreaTest(){
        List<Area> areas = areaDaoImpl.getAreas();
        String newName = "CCC";
        String newNameIsNewName = a.getName();
        Assert.assertEquals(newName, newNameIsNewName);
    }

    @Test
    public void updateAreaNameTest(){
        int result = areaDaoImpl.updateAreaName(28, "FFF");
        System.out.println(result);
    }

//    public void updateAreaTest(){
//
//        Area b = areaDaoImpl.getAreaById(27);
//        b.setComsumption_level(7);
//        int result = areaDaoImpl.updateArea(b);
//        System.out.println(result);
//    }

    public void updateTest(){
        a.setName("EEE");
        a.setConsumptionLevel(2);
        a.setDescription("DCD");
        a.setLocation("Urban");
        Area b = areaDaoImpl.getAreaByName("DDD");
        b.setName("GGG");
        boolean result = areaDaoImpl.update(b);
        String expectedNewName = "GGG";
        String actualNewName = b.getName();
        Assert.assertEquals(actualNewName, expectedNewName);
    }

    @Test
    public void deleteAreaByNameAndLocationTest(){
        areaDaoImpl.delete("EEE", "Urban");
        String deletedName = a.getName();
        Assert.assertNotNull(deletedName);
    }

    @Test
    public void getAreasTest() {
        List<Area> areas = areaDaoImpl.getAreas();
        System.out.println(areas);
    }

    @Test
    public void getAreaByNameTest(){
        Area area = areaDaoImpl.getAreaByName("VA");
        System.out.println(area);
        Assert.assertNotNull(area);
    }

    public void getAreaByIdTest(){
        Area area = areaDaoImpl.getAreaById(2);
        System.out.println(area);
        Assert.assertNotNull(area);

    }

    @After
    public void cleanup(){
        try{
            System.out.println("Enter Cleanup...");
            Area area = areaDaoImpl.getAreaByName("CCC" );
            if(area != null){
                areaDaoImpl.delete("CCC","Downtown");
                System.out.println("The record has been deleted!");
            }
            else System.out.println("Area is null");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        areaDaoImpl = null;
    }

}
