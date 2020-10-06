package com.rest_template.config_client_car.config;

import com.rest_template.config_client_car.model.Car;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@org.springframework.cloud.openfeign.FeignClient(value = "car", url = "https://springcloudconfigclient.herokuapp.com/car")
@org.springframework.cloud.openfeign.FeignClient(value = "car-ribbon-client/car")
public interface CarClient {
    @GetMapping
    public List<Car> getAllCars();

    @GetMapping("/id/{id}")
    public Car getCarById(@PathVariable Long id);

    @PostMapping
    public Car saveNewCar(@RequestBody Car car);

    @PutMapping("/update/{id}")
    public Car updateCarById(@PathVariable Long id, @RequestBody Car car);

    @DeleteMapping("/delete/{id}")
    public void deleteCarById(@PathVariable Long id);
}
