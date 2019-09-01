package com.ascending.training.april.controller;

import com.ascending.training.april.model.Hotel;
import com.ascending.training.april.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "hotels")
public class HotelController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hotel> gethotels(){
        return hotelService.getHotels();
    }

    @RequestMapping(value = "/{hotelName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel getHotelByName(@PathVariable String hotelName){
        return hotelService.getHotelByName(hotelName);
    }

    @RequestMapping(value = "{areaName}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createHotel(@RequestBody Hotel hotel, @PathVariable String areaName){
        logger.debug("hotel: " + hotel, "area:" + areaName.toString());
        String msg = "This hotel was created";
        boolean isSuccess = hotelService.save(hotel, areaName);

        if(isSuccess = false){
            msg = "This hotel was not created";
        }
        return msg;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateHotel(@RequestBody Hotel hotel){
        logger.debug("hotel:" + hotel);
        String msg = "This hotel was updated";
        boolean isSuccess = hotelService.update(hotel);

        if(isSuccess = false){
            msg = "This hotel was not updated";
        }
        return msg;
    }

    @RequestMapping(value = "{hotelName}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteHotel(@PathVariable String hotelName){
        logger.debug("hotel: " + hotelName.toString());
        String msg = "This hotel was deleted";
        int isSuccess = hotelService.delete(hotelName);

        if(isSuccess == 0){
            msg = "This hotel was not deleted";
        }
        return msg;
    }

}
