package com.carwash.carwash.adapter.repositories;

import com.carwash.carwash.adapter.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRespository extends JpaRepository<UsuarioEntity, Long> {
    
   
}
