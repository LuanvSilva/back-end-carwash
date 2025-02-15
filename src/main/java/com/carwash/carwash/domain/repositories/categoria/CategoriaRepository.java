package com.carwash.carwash.domain.Repositories.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carwash.carwash.domain.Entities.categoria.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}