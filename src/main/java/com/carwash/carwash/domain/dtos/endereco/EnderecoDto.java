package com.carwash.carwash.domain.Dtos.endereco;

import com.carwash.carwash.domain.Entities.endereco.Endereco;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EnderecoDto {
    

    Long id;
    Long empresa;
    String cep;
    String bairro;
    String cidade;
    String estado;
    String endereco;
    String numero;
    String complemento;
    public Endereco orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }
}
