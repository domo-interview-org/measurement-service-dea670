package com.homecare.visitbilling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.homecare.visitbilling.model.Tariff;

@Configuration
public class TariffConfiguration {

    @Bean
    Tariff tariff() {
        return Tariff.standard();
    }
}
