package com.carwash.carwash.domain.Dtos.autenticacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthDto {
    
    private String login;
    private String senha;

    // Getters e Setters
}