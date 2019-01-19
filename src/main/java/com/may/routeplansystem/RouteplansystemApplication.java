package com.may.routeplansystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.framework.adapter.AdvisorAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.AbstractController;

@MapperScan("com.may.routeplansystem.dao")
@SpringBootApplication
public class RouteplansystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouteplansystemApplication.class, args);
    }
}
