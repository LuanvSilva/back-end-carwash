package com.carwash.carwash.domain.entities.empresa;

import com.carwash.carwash.domain.entities.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "erp_empresas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Empresa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true, length = 100)
    private String cnpj;

    @Column(nullable = true, length = 100)
    private String razaoSocial;

    @Column(nullable = true, length = 100)
    private String nomeFantasia;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false)
    private boolean ativo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", nullable = true)
    private Endereco endereco;

}
