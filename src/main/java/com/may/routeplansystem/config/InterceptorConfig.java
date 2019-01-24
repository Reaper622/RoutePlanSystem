package com.may.routeplansystem.config;

import com.may.routeplansystem.algorithm.AlgorithmContext;
import com.may.routeplansystem.algorithm.imp.GeneticAlgorithm;
import com.may.routeplansystem.algorithm.imp.SimpleAlgorithm;
import com.may.routeplansystem.intercepter.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.client.WebSocketClient;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/**/finalSolution/**");
        urlPatterns.add("/**/node/**");
        urlPatterns.add("/**/question/**");
        urlPatterns.add("/**/vehicleSystem/**");
        urlPatterns.add("/**/algorithm/**");
        urlPatterns.add("/**/distance/**");
//        registry.addInterceptor(loginIntercepter).addPathPatterns(urlPatterns);
    }

}
