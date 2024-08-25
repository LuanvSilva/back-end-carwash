package com.carwash.carwash.adapter.converters;

import org.springframework.stereotype.Component;

import com.carwash.carwash.adapter.dtos.UsuarioDto;
import com.carwash.carwash.core.domain.Usuario;

@Component
public class UsuarioCoverter {
    
    public Usuario toUsuarioDomain(UsuarioDto usuarioDto) {
        return new Usuario(usuarioDto.getId(), 
                            usuarioDto.getNome(), 
                            usuarioDto.getEmail(), 
                            usuarioDto.getSenha(), 
                            usuarioDto.getTelefone(), 
                            usuarioDto.getEndereco(), 
                            usuarioDto.getCpfcnpj(), 
                            usuarioDto.getTipo(), 
                            usuarioDto.getStatus(),
                            usuarioDto.getDataCadastro());
    }

    public UsuarioDto toUsuarioDto(Usuario usuario) {
        return new UsuarioDto(usuario.getId(), 
                              usuario.getNome(), 
                              usuario.getEmail(), 
                              usuario.getSenha(), 
                              usuario.getTelefone(), 
                              usuario.getEndereco(), 
                              usuario.getCpfcnpj(), 
                              usuario.getTipo(), 
                              usuario.getStatus(),
                              usuario.getDataCadastro());
    }

    
}
