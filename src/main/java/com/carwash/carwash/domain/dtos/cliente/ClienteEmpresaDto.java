package com.carwash.carwash.domain.Dtos.cliente;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClienteEmpresaDto {
    
    private Long UserId;
    private Long EmpresaMoon;
    private String UserEmail;
}
