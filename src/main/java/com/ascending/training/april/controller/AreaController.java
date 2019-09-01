package com.ascending.training.april.controller;

import com.ascending.training.april.model.Area;
import com.ascending.training.april.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.SplittableRandom;

@RestController
@RequestMapping(value = {"/areas"})
public class AreaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Area> getAreas(){
        return areaService.getAreas();
    }

    @RequestMapping(value = "/{areaName}", method = RequestMethod.GET, produces = (MediaType.APPLICATION_JSON_VALUE))
    public Area getArea(@PathVariable String areaName){
        return areaService.getAreaByName(areaName);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = (MediaType.APPLICATION_JSON_VALUE))
    public String createArea(@RequestBody Area area){
        logger.debug("Area: " + area.toString());
        String msg = "The area was created";
        boolean isSuccess = areaService.save(area);

        if(!isSuccess) {msg = "The area was not created";}

        return msg;
    }

    @RequestMapping(value = "/{id}/{areaName}", method = RequestMethod.PUT, consumes = (MediaType.APPLICATION_JSON_VALUE))
    public String updateArea(@PathVariable(name="id") long areaId, @PathVariable String areaName){
        logger.debug("Area: " + areaId + areaName.toString());
        String msg = "The area was updated";
        int isSuccess = areaService.updateAreaName(areaId, areaName);

        if(isSuccess == 0){
            msg = "The area was not updated";
        }

        return msg;
    }

    @RequestMapping(value = "/{areaName}/{areaLocation}", method = RequestMethod.DELETE, consumes = (MediaType.APPLICATION_JSON_VALUE))
    public String deleteArea(@PathVariable String areaName, @PathVariable String areaLocation) {
        logger.debug(("Area:" + areaName.toString() + areaLocation.toString()));
        String msg = "The area was deleted";
        int isSuccess = areaService.delete(areaName, areaLocation);

        if (isSuccess == 0) {
            msg = "The area was not deleted";
        }

        return msg;
    }

//  @RequestMapping(value = "", method = RequestMethod.DELETE, consumes = (MediaType.APPLICATION_JSON_VALUE))
//    public String deleteArea(@RequestBody String str){
//         String[] strs = str.split(",");
//         String userName = strs[0];
//         String password = strs[1];
//         logger.info(String.format("User name: %s, password: %s", userName, password));


//        logger.debug("Area: " + areaName.toString() + areaLocation.toString() );
//        String msg = "The area was deleted";
//        int isSuccess = areaService.delete(areaName, areaLocation);
//        if(isSuccess == 0) {msg = "The area was not deleted";}
//        return str;
//    }
}
