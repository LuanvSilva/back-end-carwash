package com.carwash.carwash.domain.entities.cliente;

import com.carwash.carwash.domain.entities.empresa.Empresa;
import com.carwash.carwash.domain.entities.endereco.Endereco;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "erp_clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_moon", nullable = false)
    private Empresa empresa;

    @Column(nullable = false, unique = true, length = 20)
    private String cpfcnpj;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;

    boolean ativo;
    

}
