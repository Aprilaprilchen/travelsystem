package com.ascending.training.april;

import com.ascending.training.april.jdbc.CustomerDao;
import com.ascending.training.april.jdbc.HotelDao;
import com.ascending.training.april.model.Customer;
import com.ascending.training.april.model.Hotel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HotelDaoTest {
    private HotelDao hotelDao;

    @Before
    public void init(){
        hotelDao = new HotelDao();
    }

    @Test
    public void getHotelDao(){
        List<Hotel> hotels = hotelDao.getHotels();

        for (Hotel hotel : hotels){
            System.out.println(hotel.getName());
        }

        int ExpectedNumOfHotel = 5;
        Assert.assertEquals(ExpectedNumOfHotel, hotels.size());
    }

    @After
    public void cleanup(){
        hotelDao = null;
    }
}
