package com.ascending.training.april.controller;

import com.ascending.training.april.model.Area;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/test"})
public class CICDTestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public int test(){
        System.out.println("SUCCESS ACCESS!");
        return 200;
    }
}
