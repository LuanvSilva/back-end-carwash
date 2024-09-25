package com.carwash.carwash.domain.Controller.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carwash.carwash.domain.Dtos.cliente.ClienteDto;
import com.carwash.carwash.domain.Service.cliente.ClienteService;


@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrarCliente")
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto) {
        ClienteDto createdCliente = clienteService.createCliente(clienteDto);
        return ResponseEntity.ok(createdCliente);
    }

    @PostMapping("/buscarClienteById")
    public ResponseEntity<ClienteDto> getClienteById(@RequestBody Long id) {
        ClienteDto cliente = clienteService.getClienteById(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @GetMapping("/listarClientes") // Usando GET
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<ClienteDto> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/buscarClienteByEmpresa")
    public ResponseEntity<List<ClienteDto>> findClientesByEmpresaId(@RequestBody Long id_empresa) {
        List<ClienteDto> clientes = clienteService.findClientesByEmpresaId(id_empresa);
        return ResponseEntity.ok(clientes);
    }
}