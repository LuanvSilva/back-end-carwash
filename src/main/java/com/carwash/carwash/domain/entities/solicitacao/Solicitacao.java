package com.carwash.carwash.domain.Entities.solicitacao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.carwash.carwash.domain.Entities.cliente.Cliente;
import com.carwash.carwash.domain.Entities.empresa.Empresa;
import com.carwash.carwash.domain.Entities.item.Item;
import com.carwash.carwash.domain.Entities.status.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "erp_solicitacoes", indexes = {
    @Index(name = "idx_empresa", columnList = "empresa_moon"),
    @Index(name = "idx_cliente", columnList = "cliente_id"),
    @Index(name = "idx_item",    columnList = "item_id"),
    @Index(name = "idx_servico", columnList = "servico_id"),
    @Index(name = "idx_status",  columnList = "status_id")
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empresa_moon", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Associação para item (tipo = 1 na tabela erp_itens)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    @SQLRestriction("tipo = 1")
    private Item item;

    // Associação para serviço (tipo = 2 na tabela erp_itens)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "servico_id", nullable = false)
    @SQLRestriction("tipo = 2")
    private Item servico;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @Column(name = "data_hora_inicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(name = "data_hora_fim", nullable = false)
    private LocalDateTime dataHoraFim;

    @LastModifiedDate
    @Column(name = "data_alteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @CreatedDate
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private Boolean urgente;

    @Column(nullable = false, length = 400)
    private String observacao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
}
