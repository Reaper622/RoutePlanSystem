package com.may.routeplansystem.config;

import com.alibaba.druid.util.StringUtils;
import com.may.routeplansystem.intercepter.LoginIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config implements WebMvcConfigurer {

    @Autowired
    private LoginIntercepter loginIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/RoutePlanSystem/finalSolution/*");
        urlPatterns.add("/RoutePlanSystem/node/*");
        urlPatterns.add("/RoutePlanSystem/question/*");
        urlPatterns.add("/RoutePlanSystem/vehicleSystem/*");
        registry.addInterceptor(loginIntercepter).addPathPatterns();
    }
}
