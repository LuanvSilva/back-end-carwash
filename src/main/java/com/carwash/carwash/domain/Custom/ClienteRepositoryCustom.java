package com.carwash.carwash.domain.Custom;

import com.carwash.carwash.domain.Entities.cliente.Cliente;

import java.util.List;

public interface ClienteRepositoryCustom {

    List<Cliente> findClientesByEmpresaId(Long empresaId);
}
