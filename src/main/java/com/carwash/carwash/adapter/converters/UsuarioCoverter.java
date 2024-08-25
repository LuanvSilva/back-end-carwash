package com.carwash.carwash.adapter.converters;

import org.springframework.stereotype.Component;

import com.carwash.carwash.adapter.dtos.UsuarioDto;

@Component
public class UsuarioCoverter {
    
    public UsuarioDto toUsuarioDto(UsuarioDto usuarioDto) {
        return new UsuarioDto(usuarioDto.getId(),
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
    
}
