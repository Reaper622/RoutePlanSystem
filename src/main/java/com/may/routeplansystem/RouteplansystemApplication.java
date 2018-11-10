package com.may.routeplansystem;

import org.apache.catalina.Host;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.security.SecurityClassLoad;
import org.apache.catalina.startup.Bootstrap;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Servlet;

@MapperScan("com.may.routeplansystem.dao")
@SpringBootApplication
public class RouteplansystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouteplansystemApplication.class, args);
    }
}
