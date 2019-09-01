package com.ascending.training.april.service;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Hotel;
import com.ascending.training.april.repository.HotelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelDao hotelDao;

    public boolean save(Hotel hotel, Area area){
        return hotelDao.save(hotel, area);
    }
    public boolean save(Hotel hotel, String areaName){
        return hotelDao.save(hotel, areaName);
    }
    public boolean update(Hotel hotel){
        return hotelDao.update(hotel);
    }
    public int delete(String hotelName){
        return hotelDao.delete(hotelName);
    }
    public List<Hotel> getHotels(){
        return hotelDao.getHotels();
    }
    public Hotel getHotelByName(String hotelName){
        return hotelDao.getHotelByName(hotelName);
    }
}
