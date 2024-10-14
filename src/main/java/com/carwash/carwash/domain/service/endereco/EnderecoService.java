package com.carwash.carwash.domain.Service.endereco;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.carwash.carwash.domain.Dtos.endereco.EnderecoDto;
import com.carwash.carwash.domain.Entities.empresa.Empresa;
import com.carwash.carwash.domain.Entities.endereco.Endereco;
import com.carwash.carwash.domain.Repositories.Endereco.EnderecoRepository;
import com.carwash.carwash.util.exceptions.CustomException;
import com.carwash.carwash.util.exceptions.ErrorMessages;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EnderecoService {

     private final EnderecoRepository enderecoRepository;


    @Transactional
    public EnderecoDto createEndereco(EnderecoDto enderecoDto, Long empresa) {

        Endereco endereco = mapToEntity(enderecoDto);
        Endereco savedEndereco = enderecoRepository.save(endereco);
        return mapToDto(savedEndereco);
    }

    public EnderecoDto getEnderecoById(Long id) {
        Endereco endereco = findEnderecoById(id);
        return mapToDto(endereco);
    }

    public List<EnderecoDto> getAllEnderecos() {
        return enderecoRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public EnderecoDto updateEndereco(EnderecoDto enderecoDto) {
        Endereco endereco = findEnderecoById(enderecoDto.getId());
        Endereco updatedEndereco = mapToEntity(enderecoDto);
        updatedEndereco.setId(endereco.getId());
        return mapToDto(enderecoRepository.save(updatedEndereco));
    }

    private Endereco findEnderecoById(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessages.ENDERECO_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    private Endereco mapToEntity(EnderecoDto enderecoDto) {
        return Endereco.builder()
                .cep(enderecoDto.getCep())
                .bairro(enderecoDto.getBairro())
                .cidade(enderecoDto.getCidade())
                .estado(enderecoDto.getEstado())
                .endereco(enderecoDto.getEndereco())
                .numero(enderecoDto.getNumero())
                .complemento(enderecoDto.getComplemento())
                .build();
    }

    public EnderecoDto mapToDto(Endereco endereco) {
        return EnderecoDto.builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .endereco(endereco.getEndereco())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .build();
    }

    public Endereco mapToEntityPublic(EnderecoDto enderecoDto, Empresa empresa) {
        return Endereco.builder()
                .cep(enderecoDto.getCep())
                .bairro(enderecoDto.getBairro())
                .cidade(enderecoDto.getCidade())
                .estado(enderecoDto.getEstado())
                .endereco(enderecoDto.getEndereco())
                .numero(enderecoDto.getNumero())
                .empresa(empresa)
                .complemento(enderecoDto.getComplemento())
                .build();
    }
    
}


