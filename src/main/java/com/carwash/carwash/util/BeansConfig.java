package com.carwash.carwash.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carwash.carwash.core.ports.UsuarioServicePort;
import com.carwash.carwash.core.services.UsuarioService;

@Configuration
public class BeansConfig {
    
    @Bean
    public UsuarioServicePort usuarioServicePort() {
        return new UsuarioService();
    }
}
