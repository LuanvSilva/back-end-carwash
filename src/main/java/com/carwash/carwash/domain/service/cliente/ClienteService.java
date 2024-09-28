package com.carwash.carwash.domain.Service.cliente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.carwash.carwash.domain.Dtos.cliente.ClienteDto;
import com.carwash.carwash.domain.Entities.cliente.Cliente;
import com.carwash.carwash.domain.Entities.empresa.Empresa;
import com.carwash.carwash.domain.Repositories.cliente.ClienteRepository;
import com.carwash.carwash.domain.Repositories.empresa.EmpresaRepository;
import com.carwash.carwash.util.exceptions.CustomException;
import com.carwash.carwash.util.exceptions.ErrorMessages;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EmpresaRepository empresaRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, EmpresaRepository empresaRepository) {
        this.clienteRepository = clienteRepository;
        this.empresaRepository = empresaRepository;
    }

    public ClienteDto createCliente(ClienteDto clienteDto) {
        if (clienteRepository.existsByCpfcnpj(clienteDto.getCpfcnpj())) {
            throw new CustomException(ErrorMessages.CLIENT_ALREADY_REGISTERED, HttpStatus.BAD_REQUEST);
        }

        Empresa buscaEmpresa = empresaRepository.findById(clienteDto.getEmpresa())
                .orElseThrow(() -> new CustomException(ErrorMessages.CLIENT_NOT_FOUND + clienteDto.getEmpresa(), HttpStatus.NOT_FOUND));
        
        Cliente cliente = new Cliente();
        cliente.setName(clienteDto.getName());
        cliente.setCpfcnpj(clienteDto.getCpfcnpj());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());
        cliente.setEmpresa(buscaEmpresa);
        cliente.setAtivo(true);

        Cliente savedCliente = clienteRepository.save(cliente);
        return convertToDTO(savedCliente);
    }

    public ClienteDto getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessages.USER_NOT_FOUND + id, HttpStatus.NOT_FOUND));
        return convertToDTO(cliente);
    }

    public List<ClienteDto> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ClienteDto> findClientesByEmpresaId(Long id_empresa) {
        List<Cliente> clientes = clienteRepository.findClientesByEmpresaId(id_empresa);
        if (clientes.isEmpty()) {
            throw new CustomException(ErrorMessages.CLIENT_NOT_FOUND + id_empresa, HttpStatus.NOT_FOUND);
        }
        return clientes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ClienteDto convertToDTO(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setName(cliente.getName());
        clienteDto.setCpfcnpj(cliente.getCpfcnpj());
        clienteDto.setEmail(cliente.getEmail());
        clienteDto.setTelefone(cliente.getTelefone());
        clienteDto.setEmpresa(cliente.getEmpresa().getId());
        clienteDto.setAtivo(cliente.isAtivo());
        return clienteDto;
    }
}