package com.carwash.carwash.domain.Service.table;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.carwash.carwash.domain.Dtos.unidade.UnidadeDTO;
import com.carwash.carwash.domain.Repositories.categoria.CategoriaRepository;
import com.carwash.carwash.domain.Repositories.cliente.ClienteEmpresaRepository;
import com.carwash.carwash.domain.Repositories.empresa.EmpresaRepository;
import com.carwash.carwash.domain.Repositories.item.ItemRepository;
import com.carwash.carwash.domain.Repositories.status.StatusRepository;
import com.carwash.carwash.domain.Repositories.unidade.UnidadeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TableService {
    
    private final StatusRepository statusRepository;
    private final EmpresaRepository empresaRepository;
    private final ItemRepository itemRepository;
    private final UnidadeRepository unidadeRepository;
    private final CategoriaRepository categoriaRepository;
    private final ClienteEmpresaRepository clienteEmpresaRepository;

    private final ModelMapper modelMapper;


    @Transactional
    public List<UnidadeDTO> getUnidade() {
        return unidadeRepository.findAll().stream()
            .map(unidade -> modelMapper.map(unidade, UnidadeDTO.class))
            .toList();
    }

    @Transactional
    public List<UnidadeDTO> getStatus() {
        return statusRepository.findAll().stream()
            .map(status -> modelMapper.map(status, UnidadeDTO.class))
            .toList();
    }

    @Transactional
    public List<UnidadeDTO> getTipo() {
        return itemRepository.findAll().stream()
            .map(item -> modelMapper.map(item, UnidadeDTO.class))
            .toList();
    }

    @Transactional
    public List<UnidadeDTO> getCategoria() {
        return categoriaRepository.findAll().stream()
            .map(categoria -> modelMapper.map(categoria, UnidadeDTO.class))
            .toList();
    }
    
}
