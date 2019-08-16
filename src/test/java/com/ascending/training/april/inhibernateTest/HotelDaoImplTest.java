package com.ascending.training.april.inhibernateTest;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Customer;
import com.ascending.training.april.model.Hotel;
import com.ascending.training.april.repository.AreaDaoImpl;
import com.ascending.training.april.repository.CustomerDaoImpl;
import com.ascending.training.april.repository.HotelDaoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HotelDaoImplTest {
    private HotelDaoImpl hotelDaoimpl;
    private AreaDaoImpl areaDaoimpl;
    private Hotel h;
    private Area a;

    @Before
    public void init(){
        hotelDaoimpl = new HotelDaoImpl();
        areaDaoimpl = new AreaDaoImpl();
        h = new Hotel();
        a = areaDaoimpl.getAreaByName("VA");
        h.setName("Home Hotel");
        h.setLocation("Urban");
        h.setPrice(190);
        h.setComfortLevel(4);

        boolean result = hotelDaoimpl.save(h, a);
        System.out.println(result);
    }

    @After
    public void cleanup(){
        try{
            System.out.println("Enter Cleanup...");
            Hotel hotel = hotelDaoimpl.getHotelByName("Home Hotel" );
            if(hotel != null){
                hotelDaoimpl.delete("Home Hotel");
                System.out.println("The record has been deleted!");
            }
            else System.out.println("hotel is null");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        hotelDaoimpl = null;
    }

    @Test
    public void saveHotelTest() {
        String newHotel = "Home Hotel";
        String realNewHotel = h.getName();
        Assert.assertEquals(newHotel, realNewHotel);
    }

    @Test
    public void updateHotelTest(){
        h.setPrice(200);
        boolean result = hotelDaoimpl.update(h);
        int expectedPrice = 200;
        int realPrice = h.getPrice();
        Assert.assertEquals(expectedPrice, realPrice);
        System.out.println(result);
    }

    @Test
    public void deleteHotelTest(){
        int count = hotelDaoimpl.delete( "Home Hotel");
        int expectedOfNum = 1;
        Assert.assertEquals(expectedOfNum, count);
    }

    @Test
    public void getHotelsTest(){
        List<Hotel> hotels = hotelDaoimpl.getHotels();
        System.out.println(hotels);
    }

    @Test
    public void getCustomerByName(){
        Hotel hotels = hotelDaoimpl.getHotelByName("Green Tree");
        Assert.assertNotNull(hotels);
    }
}
