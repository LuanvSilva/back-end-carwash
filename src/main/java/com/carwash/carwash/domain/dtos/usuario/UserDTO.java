package com.carwash.carwash.domain.dtos.usuario;
import com.carwash.carwash.domain.entities.empresa.Empresa;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Empresa empresa;
    private boolean ativo;

}
