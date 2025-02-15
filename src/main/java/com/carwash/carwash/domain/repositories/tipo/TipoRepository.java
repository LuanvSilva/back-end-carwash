package com.carwash.carwash.domain.Repositories.tipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carwash.carwash.domain.Entities.tipo.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {
}