package com.carwash.carwash.domain.Repositories.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carwash.carwash.domain.Entities.usuario.Usuario;
import com.carwash.carwash.domain.Service.Impl.cliente.ClienteEmpresaProjection;

public interface ClienteEmpresaRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u.id as userId, e.id as empresaMoon, u.email as userEmail " +
           "FROM Usuario u " +
           "JOIN u.empresa e " +
           "WHERE u.email = :email")
    List<ClienteEmpresaProjection> findClienteAndEmpresa(@Param("email") String email);
}
