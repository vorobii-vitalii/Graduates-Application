package com.pnu.db.Graduates.Application.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
