package com.carwash.carwash.domain.Dtos.solicitacao;


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
public class SolicitacaoDTO {
    private Long id;
    private Long empresaId;
    private Long clienteId;
    private Long itemId;
    private Long servicoId;
    private Long statusId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataCriacao;
    private Boolean urgente;
    private String observacao;
    private BigDecimal valor;
}