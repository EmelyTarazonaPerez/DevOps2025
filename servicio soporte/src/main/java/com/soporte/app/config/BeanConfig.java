package com.soporte.app.config;

import com.soporte.app.domain.servicio.ServiceCardImp;
import com.soporte.app.domain.servicio.ServiceOrderImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ServiceOrderImp serviceOrderImp() {
        return new ServiceOrderImp();
    }

    @Bean
    public ServiceCardImp serviceCardImp() {
        return new ServiceCardImp();
    }
}
