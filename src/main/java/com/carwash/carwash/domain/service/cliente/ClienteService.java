package com.carwash.carwash.domain.Service.cliente;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.carwash.carwash.domain.Dtos.cliente.ClienteDto;
import com.carwash.carwash.domain.Dtos.endereco.EnderecoDto;
import com.carwash.carwash.domain.Entities.cliente.Cliente;
import com.carwash.carwash.domain.Entities.empresa.Empresa;
import com.carwash.carwash.domain.Entities.endereco.Endereco;
import com.carwash.carwash.domain.Repositories.Endereco.EnderecoRepository;
import com.carwash.carwash.domain.Repositories.cliente.ClienteRepository;
import com.carwash.carwash.domain.Repositories.empresa.EmpresaRepository;
import com.carwash.carwash.domain.Service.endereco.EnderecoService;
import com.carwash.carwash.util.constantes.Constantes;
import com.carwash.carwash.util.exceptions.CustomException;
import com.carwash.carwash.util.exceptions.ErrorMessages;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EmpresaRepository empresaRepository;
    private final EnderecoRepository enderecoRepository;
    private final EnderecoService enderecoService;

    @Transactional
    public ClienteDto createCliente(ClienteDto clienteDto) {

        validateNewCliente(clienteDto);
        
        EnderecoDto enderecoDto = enderecoService.createEndereco(clienteDto.getEndereco(), clienteDto.getEmpresa());
        Empresa empresa = findEmpresaById(clienteDto.getEmpresa());

        // Associar o endereço cadastrado ao cliente
        Cliente cliente = mapToEntity(clienteDto, empresa, enderecoDto);
        Cliente savedCliente = clienteRepository.save(cliente);
        return mapToDto(savedCliente);
    }

    @Transactional
    public ClienteDto updateCliente(ClienteDto clienteDto) {
        
        // Cliente cliente = findClienteById(clienteDto.getId());
        // //Endereco endereco = findEnderecoById(clienteDto.getEndereco());
        // Empresa empresa = findEmpresaById(clienteDto.getEmpresa());

        // //Cliente updatedCliente = mapToEntity(clienteDto, empresa, endereco);
        // updatedCliente.setId(cliente.getId()); // Preserve ID during update
        // return mapToDto(clienteRepository.save(updatedCliente));
        return null;
    }

    public ClienteDto getClienteById(Long id) {
        Cliente cliente = findClienteById(id);
        return mapToDto(cliente);
    }

    public List<ClienteDto> getAllClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ClienteDto> findClientesByEmpresaId(Long empresaId) {
        List<Cliente> clientes = clienteRepository.findClientesByEmpresaId(empresaId);
        if (clientes.isEmpty()) {
            throw new CustomException(ErrorMessages.CLIENT_NOT_FOUND + empresaId, HttpStatus.NOT_FOUND);
        }
        return clientes.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private void validateNewCliente(ClienteDto clienteDto) {
        if (!clienteDto.getEmail().matches(Constantes.EMAIL_VALIDATION_REGEX)) {
            throw new CustomException(ErrorMessages.INVALID_EMAIL + clienteDto.getEmail(), HttpStatus.BAD_REQUEST);
        }
    }

    private Endereco findEnderecoById(Long enderecoId) {
        return enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new CustomException(ErrorMessages.ADDRESS_NOT_FOUND + enderecoId, HttpStatus.NOT_FOUND));
    }

    private Empresa findEmpresaById(Long empresaId) {
        return empresaRepository.findById(empresaId)
                .orElseThrow(() -> new CustomException(ErrorMessages.COMPANY_NOT_FOUND + empresaId, HttpStatus.NOT_FOUND));
    }

    private Cliente findClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessages.CLIENT_NOT_FOUND + id, HttpStatus.NOT_FOUND));
    }

    private Cliente mapToEntity(ClienteDto clienteDto, Empresa empresa, EnderecoDto enderecoDto) {
        
        Endereco endereco = enderecoService.mapToEntityPublic(enderecoDto, findEmpresaById(empresa.getId()));

        return Cliente.builder()
            .name(clienteDto.getName())
            .cpfcnpj(clienteDto.getCpfcnpj())
            .email(clienteDto.getEmail())
            .telefone(clienteDto.getTelefone())
            .empresa(empresa)
            .endereco(endereco)
            .ativo(true)
            .build();
    }

    private ClienteDto mapToDto(Cliente cliente) {
        return ClienteDto.builder()
                .id(cliente.getId())
                .name(cliente.getName())
                .cpfcnpj(cliente.getCpfcnpj())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .empresa(cliente.getEmpresa().getId())
                .endereco(enderecoService.mapToDto(cliente.getEndereco())) 
                .ativo(cliente.isAtivo())
                .build();
    }
}