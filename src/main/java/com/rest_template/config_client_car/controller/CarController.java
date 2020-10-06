package com.rest_template.config_client_car.controller;

import com.rest_template.config_client_car.model.Car;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@AllArgsConstructor
public class CarController {
    private RestTemplate restTemplate;
    private LoadBalancerClient loadBalancer;

    @GetMapping
    public List<Car> stringList() {
//        String uri = "https://springcloudconfigclient.herokuapp.com/car";
        String uri = "https://car-ribbon-client/car";
        List<Car> carList = Arrays.asList(restTemplate.getForObject(uri, Car[].class));
        return carList;
    }

    @GetMapping("/id/{id}")
    public Car findCar(@PathVariable Long id) {
        String uri = "https://springcloudconfigclient.herokuapp.com/car/id/{id}";
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        Car car = restTemplate.getForObject(uri, Car.class, params);
        return car;
    }

    @PostMapping
    public Car saveCar(@RequestBody Car car) {
        String uri = "https://springcloudconfigclient.herokuapp.com/car";
        Car newCar = restTemplate.postForObject(uri, car, Car.class);
        return newCar;
    }

    @PutMapping("update/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        String uri = "https://springcloudconfigclient.herokuapp.com/car/update/" + id;
//        Map<String, Long> params = new HashMap<>();
//        params.put("id", id);
//        restTemplate.put(uri, car, params);
//        return restTemplate.getForObject(uri1, Car.class, params);
        HttpEntity<Car> httpEntity = new HttpEntity<>(car, null);
        ResponseEntity<Car> response = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, Car.class);
        return  response.getBody();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCarById(@PathVariable Long id) {
        String uri = "https://springcloudconfigclient.herokuapp.com/car/delete/" + id;
        HttpEntity<String> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, Void.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return "Deleted";
        } else
            return "Not deleted";
    }

    @GetMapping("/balancer")
    public String loadBalancerClient() {
        return loadBalancer.toString();
    }
}
