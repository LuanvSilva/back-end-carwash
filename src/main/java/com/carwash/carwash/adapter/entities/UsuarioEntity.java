package com.carwash.carwash.adapter.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String endereco;
    private String cpfcnpj;
    private String tipo;
    private String status;
    private String dataCadastro;

}
