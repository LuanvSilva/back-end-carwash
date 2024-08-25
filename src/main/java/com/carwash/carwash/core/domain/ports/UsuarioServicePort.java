package com.carwash.carwash.core.domain.ports;

import com.carwash.carwash.adapter.dtos.UsuarioDto;

public interface UsuarioServicePort {
    
    public UsuarioDto cadastrarUsuario(UsuarioDto usuarioDto);

}
