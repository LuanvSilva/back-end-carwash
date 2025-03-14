package com.carwash.carwash.domain.Dtos.table.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableItemDto {
    private Long itemId;
    private String itemDescricao;
    private String itemCodigo;
    private Long categoriaId;
    private String categoriaDescricao;
    private String categoriaCodigo;
    private Long unidadeId;
    private String unidadeDescricao;
    private String unidadeCodigo;
    private Long tipoId;
    private String tipoDescricao;
    private String tipoCodigo;
}
