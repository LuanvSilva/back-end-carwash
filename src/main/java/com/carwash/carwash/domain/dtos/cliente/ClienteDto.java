package com.carwash.carwash.domain.Dtos.cliente;

import com.carwash.carwash.domain.Dtos.endereco.EnderecoDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClienteDto {
    
    private Long id;
    private Long empresa;
    private String cpfcnpj;
    private String name;
    private String email;
    private String telefone;
    private EnderecoDto endereco;
    private boolean ativo;
}
