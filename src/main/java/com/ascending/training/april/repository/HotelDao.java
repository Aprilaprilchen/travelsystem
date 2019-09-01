package com.ascending.training.april.repository;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.Hotel;

import java.util.List;

public interface HotelDao {
    boolean save(Hotel hotel, Area area);
    boolean save(Hotel hotel, String areaName);
    boolean update(Hotel hotel);
    int delete(String hotelName);
    List<Hotel> getHotels();
    Hotel getHotelByName(String hotelName);
}
