package com.oliwa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class VvarriorsApp implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(VvarriorsApp.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //RedirectViewControllerRegistration r = registry.addRedirectViewController("/", "/login");
    }
}
