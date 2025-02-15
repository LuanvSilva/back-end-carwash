package com.carwash.carwash.domain.Dtos.unidade;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeDTO {
    private Long id;
    private String codigo;
    private String descricao;
    private LocalDateTime dataHoraCadastro;
    private LocalDateTime dataHoraAtualizacao;
    private Boolean ativo;
}