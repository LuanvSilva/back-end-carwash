package com.carwash.carwash.domain.dtos.usuario;

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
    private Long empresa;
    private boolean ativo;

}
