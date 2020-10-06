package com.rest_template.config_client_car.controller;

import com.rest_template.config_client_car.model.Car;
import com.rest_template.config_client_car.model.CarDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/dto")
@AllArgsConstructor
public class CarDTOController {
    private RestTemplate restTemplate;
    private ModelMapper modelMapper;

    @GetMapping
    public List<CarDTO> getAllCarDTOs() {
        String uri = "https://springcloudconfigclient.herokuapp.com/car";
        List<Car> carList = Arrays.asList(restTemplate.getForObject(uri, Car[].class));
        return carList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public CarDTO findDTOById(@PathVariable Long id) {
        String uri = "https://springcloudconfigclient.herokuapp.com/car/id/{id}";
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        Car car = restTemplate.getForObject(uri, Car.class, params);
        CarDTO carDTO = convertToDTO(car);
        return carDTO;
    }

    @PostMapping
    public CarDTO saveCarDTO(@RequestBody CarDTO carDTO) {
        String uri = "https://springcloudconfigclient.herokuapp.com/car";
        Car car = convertDTOToCar(carDTO);
        Car newCar = restTemplate.postForObject(uri, car, Car.class);
        return convertToDTO(newCar);
    }

    public CarDTO convertToDTO(Car car) {
        CarDTO carDTO = modelMapper.map(car, CarDTO.class);
        return carDTO;
    }

    public Car convertDTOToCar(CarDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);
        return car;
    }
}
