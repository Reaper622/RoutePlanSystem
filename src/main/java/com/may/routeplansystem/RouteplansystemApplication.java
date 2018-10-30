package com.may.routeplansystem;

import org.apache.catalina.connector.Connector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

@MapperScan("com.may.routeplansystem.dao")
@SpringBootApplication
public class RouteplansystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouteplansystemApplication.class, args);
    }
}
