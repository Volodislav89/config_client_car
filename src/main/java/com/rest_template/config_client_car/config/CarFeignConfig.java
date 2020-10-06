package com.rest_template.config_client_car.config;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.ribbon.LoadBalancingTarget;

@Configuration
@EnableFeignClients
@RibbonClient(name = "car-ribbon-client")
public class CarFeignConfig {

    @Value("${feign.userclient.url}")
    String feignClientUrl;

    @Bean
    CarClient carFeignClient() {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
//                .target(FeignClient.class, feignClientUrl);
                .target(LoadBalancingTarget.create(CarClient.class, "https://car-ribbon-client"));
    }
}
