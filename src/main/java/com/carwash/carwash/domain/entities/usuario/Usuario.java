package com.carwash.carwash.domain.entities.usuario;

import com.carwash.carwash.domain.entities.empresa.Empresa;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "moon_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_moon", nullable = false)
    private Empresa empresa;

    @Column(nullable = true, length = 100)
    private String name;

    @Column(nullable = true, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    boolean ativo;


}
