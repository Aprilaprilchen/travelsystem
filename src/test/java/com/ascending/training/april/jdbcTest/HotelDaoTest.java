package com.ascending.training.april.jdbcTest;

import com.ascending.training.april.jdbc.AreaDao;
import com.ascending.training.april.jdbc.CustomerDao;
import com.ascending.training.april.jdbc.HotelDao;
import com.ascending.training.april.model.Customer;
import com.ascending.training.april.model.Hotel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class HotelDaoTest {
    private HotelDao hotelDao;
    private Hotel hotel;
    private String hotelName;

    @Before
    public void init(){
        hotelDao = new HotelDao();
        hotel = new Hotel();
//        areaDao = new AreaDao();
//        area = areaDao.getAreaByName("DC");

        hotelName = "CytHouse";
        hotel.setName(hotelName);
        hotel.setLocation("Falls Church");
        hotel.setPrice(new BigDecimal(100000));
        hotel.setComfortLevel(10);
        hotelDao.insertHotel(hotel);
    }

    @Test
    public void getHotelDao(){
        List<Hotel> hotels = hotelDao.getHotels();

        for (Hotel hotel : hotels){
            System.out.println(hotel.getName());
        }
//for me:
//        int ExpectedNumOfHotel = 6;
//for test:
          int ExpectedNumOfHotel = 1;
        Assert.assertEquals(ExpectedNumOfHotel, hotels.size());
    }

    @After
    public void cleanup(){
        if (hotel!=null)hotelDao.deleteHotelByName(hotelName);
        hotelDao = null;
    }
}
