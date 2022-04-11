package com.myshop.springconfig.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final ObjectMapper mapper;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error/forbidden").setViewName("error/forbidden");
        registry.addViewController("/error/notFound").setViewName("error/notFound");
        registry.addViewController("/my/main").setViewName("my/myMain");
        registry.addViewController("/admin/main").setViewName("admin/adminMain");
        registry.addViewController("/loggedOut").setViewName("loggedOut");
    }
}
