package com.ascending.training.april.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = ("com.ascending.training.april"))
@ServletComponentScan(basePackages = ("com.ascending.training.april"))
public class AppInitializer {
    public static void main(String[] args){
        SpringApplication.run(AppInitializer.class, args);
    }
}
