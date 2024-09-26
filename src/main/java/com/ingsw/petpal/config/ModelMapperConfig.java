package com.ingsw.petpal.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // para q se comporte como elemento de configuracion
public class ModelMapperConfig {
    @Bean // para que sea transaccional
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
