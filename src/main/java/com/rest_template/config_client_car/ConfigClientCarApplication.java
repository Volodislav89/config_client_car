package com.rest_template.config_client_car;

import com.rest_template.config_client_car.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication

//@RibbonClient(name = "ribbon-client", configuration = RibbonConfiguration.class)
public class ConfigClientCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientCarApplication.class, args);
    }

}
