package com.carwash.carwash.domain.repositories.usuario;

import com.carwash.carwash.domain.entities.usuario.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    boolean existsById(Long id); // Corrigido de Long para boolean

    Usuario findByEmail(String email);
    
}
