package com.carwash.carwash.adapter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carwash.carwash.adapter.converters.UsuarioCoverter;
import com.carwash.carwash.adapter.dtos.UsuarioDto;
import com.carwash.carwash.core.domain.ports.UsuarioServicePort;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServicePort usuarioServicePort;
    private final UsuarioCoverter usuarioCoverter;
    
    @PostMapping("/cadastrar")
    public UsuarioDto cadastrarUsuario(@RequestBody UsuarioDto usuarioDto) {

        System.out.println(usuarioDto);  
        return usuarioServicePort.cadastrarUsuario(usuarioCoverter.toUsuarioDto(usuarioDto)); 
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }
}
