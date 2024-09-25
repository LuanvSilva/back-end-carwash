package com.carwash.carwash.domain.Dtos.cliente;

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
    private Long endereco;
    private boolean ativo;
}
