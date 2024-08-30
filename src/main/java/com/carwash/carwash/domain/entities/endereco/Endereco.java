package com.carwash.carwash.domain.entities.endereco;

import com.carwash.carwash.domain.entities.empresa.Empresa;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "erp_enderecos")
public class Endereco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_moon", nullable = false)
    private Empresa empresa;

    @Column(nullable = true, length = 20)
    private String cep;

    @Column(nullable = true, length = 100)
    private String bairro;

    @Column(nullable = true, length = 100)
    private String cidade;

    @Column(nullable = false, length = 100)
    private String estado;

    @Column(nullable = false, length = 100)
    private String endereco;

    @Column(nullable = true, length = 10)
    private String numero;

    @Column(nullable = false, length = 155)
    private String complemento;

   
}
