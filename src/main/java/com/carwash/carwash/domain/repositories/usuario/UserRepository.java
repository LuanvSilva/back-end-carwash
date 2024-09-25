package com.carwash.carwash.domain.Repositories.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carwash.carwash.domain.Entities.usuario.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    boolean existsById(Long id); // Corrigido de Long para boolean

    Usuario findByEmail(String email);
    
}
