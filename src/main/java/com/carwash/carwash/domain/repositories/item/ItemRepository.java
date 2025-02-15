package com.carwash.carwash.domain.Repositories.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carwash.carwash.domain.Entities.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE i.empresa.id = :empresaId")
    List<Item> findItemsByEmpresaId(@Param("empresaId") Long empresaId);
}
