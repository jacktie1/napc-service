package org.apathinternational.faithpathrestful.config;

import org.apathinternational.faithpathrestful.config.interceptor.ResponseHeadersInterceptor;
import org.apathinternational.faithpathrestful.config.interceptor.ThreadContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ThreadContextInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new ResponseHeadersInterceptor()).addPathPatterns("/**");
    }
}