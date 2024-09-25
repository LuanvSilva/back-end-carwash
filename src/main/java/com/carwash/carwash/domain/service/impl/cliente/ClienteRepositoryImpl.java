package com.carwash.carwash.domain.Service.Impl.cliente;


import com.carwash.carwash.domain.Custom.ClienteRepositoryCustom;
import com.carwash.carwash.domain.Entities.cliente.Cliente;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ClienteRepositoryImpl implements ClienteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cliente> findClientesByEmpresaId(Long empresaId) {
        TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.empresa.id = :empresaId", Cliente.class);
        query.setParameter("empresaId", empresaId);
        return query.getResultList();
    }
}
