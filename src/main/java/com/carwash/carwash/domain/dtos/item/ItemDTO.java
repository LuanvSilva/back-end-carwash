package com.carwash.carwash.domain.Dtos.item;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private Long empresa;
    private Long tipoId;
    private Long unidadeId;
    private Long categoriaId;
    private String codigo;
    private String descricao;
    private LocalDateTime dataHoraCadastro;
    private LocalDateTime dataHoraAtualizacao;
    private BigDecimal valor;
    private Boolean ativo;
    private String observacao;
}