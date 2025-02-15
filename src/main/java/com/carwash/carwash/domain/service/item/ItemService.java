package com.carwash.carwash.domain.Service.item;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carwash.carwash.domain.Dtos.item.ItemDTO;
import com.carwash.carwash.domain.Entities.categoria.Categoria;
import com.carwash.carwash.domain.Entities.empresa.Empresa;
import com.carwash.carwash.domain.Entities.item.Item;
import com.carwash.carwash.domain.Entities.tipo.Tipo;
import com.carwash.carwash.domain.Entities.unidade.Unidade;
import com.carwash.carwash.domain.Repositories.categoria.CategoriaRepository;
import com.carwash.carwash.domain.Repositories.empresa.EmpresaRepository;
import com.carwash.carwash.domain.Repositories.item.ItemRepository;
import com.carwash.carwash.domain.Repositories.tipo.TipoRepository;
import com.carwash.carwash.domain.Repositories.unidade.UnidadeRepository;
import com.carwash.carwash.domain.Service.empresa.EmpresaService;
import com.carwash.carwash.util.exceptions.CustomException;
import com.carwash.carwash.util.exceptions.ErrorMessages;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final EmpresaRepository empresaRepository;
    private final CategoriaRepository categoriaRepository;
    private final UnidadeRepository unidadeRepository;
    private final TipoRepository tipoRepository;

    @Transactional
    public ItemDTO createItem(ItemDTO itemDTO, Long empresaId) {

        Empresa empresa = findEmpresaById(empresaId);
        Categoria categoria = findCategoriaById(itemDTO.getCategoriaId());
        Unidade unidade = findUnidadeById(itemDTO.getUnidadeId());
        Tipo tipo = findTipoById(itemDTO.getTipoId());
        
        Item item = mapToEntity(itemDTO, empresa, categoria, unidade, tipo);
        item = itemRepository.save(item);
        return mapToDto(item);
    }

    @Transactional(readOnly = true)
    public ItemDTO getItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new CustomException(ErrorMessages.ITEM_NOT_FOUND + id, HttpStatus.NOT_FOUND));
        return mapToDto(item);
    }

    @Transactional(readOnly = true)
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ItemDTO> findItemsByEmpresaId(Long empresaId) {
        return itemRepository.findItemsByEmpresaId(empresaId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Transactional
    public ItemDTO updateItem(ItemDTO itemDTO, Long empresaId) {
        Item item = itemRepository.findById(itemDTO.getId()).orElseThrow(() -> new CustomException(ErrorMessages.ITEM_NOT_FOUND + itemDTO.getId(), HttpStatus.NOT_FOUND));
        Empresa empresa = findEmpresaById(empresaId);
        Categoria categoria = findCategoriaById(itemDTO.getCategoriaId());
        Unidade unidade = findUnidadeById(itemDTO.getUnidadeId());
        Tipo tipo = findTipoById(itemDTO.getTipoId());
        updateEntityFromDto(itemDTO, item, empresa, categoria, unidade, tipo);
        item = itemRepository.save(item);
        return mapToDto(item);
    }

    @Transactional
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Transactional
    private Empresa findEmpresaById(Long empresaId) {
         return empresaRepository.findById(empresaId)
                .orElseThrow(() -> new CustomException(ErrorMessages.COMPANY_NOT_FOUND + empresaId, HttpStatus.NOT_FOUND));
    }

    @Transactional
    private Tipo findTipoById(Long tipoId) {
        return tipoRepository.findById(tipoId)
                .orElseThrow(() -> new CustomException(ErrorMessages.TYPE_NOT_FOUND + tipoId, HttpStatus.NOT_FOUND));
    }

    @Transactional
    private Unidade findUnidadeById(Long unidadeId) {
        return unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new CustomException(ErrorMessages.UNIT_NOT_FOUND + unidadeId, HttpStatus.NOT_FOUND));
    }

    @Transactional
    private Categoria findCategoriaById(Long categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new CustomException(ErrorMessages.CATEGORY_NOT_FOUND + categoriaId, HttpStatus.NOT_FOUND));
    }

    private Item mapToEntity(ItemDTO itemDTO, Empresa empresa, Categoria categoria, Unidade unidade, Tipo tipo) {

        return Item.builder()
                .id(itemDTO.getId())
                .empresa(empresa)
                .tipo(tipo)
                .unidade(unidade)
                .categoria(categoria)
                .codigo(itemDTO.getCodigo())
                .descricao(itemDTO.getDescricao())
                .dataHoraCadastro(itemDTO.getDataHoraCadastro())
                .dataHoraAtualizacao(itemDTO.getDataHoraAtualizacao())
                .valor(itemDTO.getValor())
                .ativo(itemDTO.getAtivo())
                .observacao(itemDTO.getObservacao())
                .build();
    }

    private ItemDTO mapToDto(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .empresa(item.getEmpresa().getId())
                .tipoId(item.getTipo().getId())
                .unidadeId(item.getUnidade().getId())
                .categoriaId(item.getCategoria().getId())
                .codigo(item.getCodigo())
                .descricao(item.getDescricao())
                .dataHoraCadastro(item.getDataHoraCadastro())
                .dataHoraAtualizacao(item.getDataHoraAtualizacao())
                .valor(item.getValor())
                .ativo(item.getAtivo())
                .observacao(item.getObservacao())
                .build();
    }

    private void updateEntityFromDto(ItemDTO itemDTO, Item item, Empresa empresa, Categoria categoria, Unidade unidade, Tipo tipo) {
        item.setEmpresa(empresa);
        item.setTipo(tipo);
        item.setUnidade(unidade);
        item.setCategoria(categoria);
        item.setCodigo(itemDTO.getCodigo());
        item.setDescricao(itemDTO.getDescricao());
        item.setDataHoraCadastro(itemDTO.getDataHoraCadastro());
        item.setDataHoraAtualizacao(itemDTO.getDataHoraAtualizacao());
        item.setValor(itemDTO.getValor());
        item.setAtivo(itemDTO.getAtivo());
        item.setObservacao(itemDTO.getObservacao());
    }
}