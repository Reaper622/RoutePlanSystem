package com.may.routeplansystem;

import org.apache.catalina.connector.Connector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mail.javamail.JavaMailSender;

@MapperScan("com.may.routeplansystem.dao")
@SpringBootApplication
public class RouteplansystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouteplansystemApplication.class, args);
    }
}
