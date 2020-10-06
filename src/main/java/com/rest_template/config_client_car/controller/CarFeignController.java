package com.rest_template.config_client_car.controller;

import com.rest_template.config_client_car.config.CarClient;
import com.rest_template.config_client_car.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feign")
public class CarFeignController {
    @Autowired
    CarClient carFeignClient;

    @GetMapping
    public List<Car> getAllCars() {
        return carFeignClient.getAllCars();
    }
}
