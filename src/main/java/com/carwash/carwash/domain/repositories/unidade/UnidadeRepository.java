package com.carwash.carwash.domain.Repositories.unidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carwash.carwash.domain.Entities.unidade.Unidade;
@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
}