package com.carwash.carwash.domain.Repositories.table.item;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.carwash.carwash.domain.Dtos.table.item.ItemSelectDto;
import com.carwash.carwash.domain.Dtos.table.item.ServicoDto;
import com.carwash.carwash.domain.Dtos.table.item.TableItemDto;
import com.carwash.carwash.domain.Entities.item.Item;

@Repository
public interface TableItemRepository extends CrudRepository<Item, Long> {

    @Query("SELECT new com.carwash.carwash.domain.Dtos.table.item.TableItemDto(" +
           "i.id, i.descricao, i.codigo, i.observacao, i.ativo," +
           "c.id, c.descricao, c.codigo, " +
           "u.id, u.descricao, u.codigo, " +
           "t.id, t.descricao, t.codigo) " +
           "FROM Item i " +
           "JOIN i.categoria c " +
           "JOIN i.unidade u " +
           "JOIN i.tipo t " +
           "WHERE i.empresa.id = :empresaId")
    List<TableItemDto> findTableItemsByEmpresaId(@Param("empresaId") Long empresaId);


    @Query("SELECT new com.carwash.carwash.domain.Dtos.table.item.ServicoDto(i.id, i.codigo, i.descricao) FROM Item i WHERE i.tipo.id = :tipoId AND i.ativo = true")
    List<ServicoDto> findActiveServices(@Param("tipoId") int tipoId);

    @Query("SELECT new com.carwash.carwash.domain.Dtos.table.item.ItemSelectDto(i.id, i.codigo, i.descricao) FROM Item i WHERE i.tipo.id = :tipoId AND i.ativo = true")
    List<ItemSelectDto> findActiveItems(@Param("tipoId") int tipoId);

}
