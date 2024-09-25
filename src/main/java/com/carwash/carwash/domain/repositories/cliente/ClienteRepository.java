package com.carwash.carwash.domain.Repositories.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carwash.carwash.domain.Custom.ClienteRepositoryCustom;
import com.carwash.carwash.domain.Entities.cliente.Cliente;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryCustom {
    
    boolean existsByCpfcnpj(String cpfcnpj); 

    boolean existsById(Long id);

    Cliente findByCpfcnpj(String cpfcnpj); 

    @Query("SELECT c FROM Cliente c WHERE c.empresa.id = :empresaId")
    List<Cliente> findClientesByEmpresaId(@Param("empresaId") Long empresaId);
}
