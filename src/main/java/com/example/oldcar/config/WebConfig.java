package com.example.oldcar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/20
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //指向外部目录
        registry.addResourceHandler("img//**").addResourceLocations("file:C:/img/");
    }
}
