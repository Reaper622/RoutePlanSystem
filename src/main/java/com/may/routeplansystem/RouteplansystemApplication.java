package com.may.routeplansystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.cors.DefaultCorsProcessor;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Hashtable;

@MapperScan("com.may.routeplansystem.dao")
@SpringBootApplication
public class RouteplansystemApplication{
    public static void main(String[] args) {
        SpringApplication.run(RouteplansystemApplication.class, args);
    }
}
