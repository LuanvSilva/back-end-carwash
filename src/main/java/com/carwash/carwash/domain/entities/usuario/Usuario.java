package com.carwash.carwash.domain.Entities.usuario;

import com.carwash.carwash.domain.Entities.empresa.Empresa;

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

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    boolean ativo;


}
