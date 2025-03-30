package ru.volkovd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Разрешить CORS для всех эндпоинтов
                        .allowedOrigins("*") // Разрешить запросы с любого домена
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешить методы
                        .allowedHeaders("*") // Разрешить все заголовки
                        .allowCredentials(false); // Отключить передачу куки
            }
        };
    }
}