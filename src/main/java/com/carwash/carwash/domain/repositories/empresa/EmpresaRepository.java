package com.carwash.carwash.domain.repositories.empresa;

import com.carwash.carwash.domain.entities.empresa.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
}
