package com.carwash.carwash.core.services;

import com.carwash.carwash.core.domain.Usuario;
import com.carwash.carwash.core.ports.UsuarioServicePort;

public class UsuarioService implements UsuarioServicePort {

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuario;
    }
    
}
