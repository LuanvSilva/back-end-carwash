package com.carwash.carwash.domain.Dtos.table.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSelectDto {
    
    private Long id;
    private String codigo;
    private String descricao;
}
