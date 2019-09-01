package com.ascending.training.april.controller;


import com.ascending.training.april.model.Area;
import com.ascending.training.april.model.User;
import com.ascending.training.april.service.AreaService;
import com.ascending.training.april.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/users"})
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{email}", method = RequestMethod.GET, produces = "application/json")
    public User user(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET, produces = (MediaType.APPLICATION_JSON_VALUE))
//    public Area getArea(@PathVariable String areaName){
//        return userService.getUserByCredentials(areaName);
//    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = (MediaType.APPLICATION_JSON_VALUE))
    public String createArea(@RequestBody User user) {
        logger.debug("User: " + user.toString());
        String msg = "The user was created";
        boolean isSuccess = userService.save(user);

        if (!isSuccess) {
            msg = "The user was not created";
        }

        return msg;
    }
}

//    @RequestMapping(value = "/{id}/{areaName}", method = RequestMethod.PUT, consumes = (MediaType.APPLICATION_JSON_VALUE))
//    public String updateArea(@PathVariable(name="id") long areaId, @PathVariable String areaName){
//        logger.debug("Area: " + areaId + areaName.toString());
//        String msg = "The area was updated";
//        int isSuccess = areaService.updateAreaName(areaId, areaName);
//
//        if(isSuccess == 0){
//            msg = "The area was not updated";
//        }
//
//        return msg;
//    }
//
//    @RequestMapping(value = "/{areaName}/{areaLocation}", method = RequestMethod.DELETE, consumes = (MediaType.APPLICATION_JSON_VALUE))
//    public String deleteArea(@PathVariable String areaName, @PathVariable String areaLocation) {
//        logger.debug(("Area:" + areaName.toString() + areaLocation.toString()));
//        String msg = "The area was deleted";
//        int isSuccess = areaService.delete(areaName, areaLocation);
//
//        if (isSuccess == 0) {
//            msg = "The area was not deleted";
//        }
//
//        return msg;
//    }