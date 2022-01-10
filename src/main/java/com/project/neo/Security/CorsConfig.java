package com.project.neo.Security;

/* Reference 1 - This class was referenced from: https://stackoverflow.com/a/63423537 */
// This class configures CORS

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig{

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/login/**")
//                registry.addMapping("/**")

                        .allowedOrigins("http://localhost:3000")
                        .allowedHeaders("*")
                        .allowedMethods("*");
            }
        };
    }
}
