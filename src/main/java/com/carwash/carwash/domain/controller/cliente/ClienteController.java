package com.carwash.carwash.domain.Controller.cliente;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carwash.carwash.domain.Dtos.cliente.ClienteDto;
import com.carwash.carwash.domain.Service.cliente.ClienteService;
import com.carwash.carwash.util.exceptions.CustomException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
public class ClienteController {
    

    private final ClienteService clienteService;

    @PostMapping("/createClient")
    public ResponseEntity<ClienteDto> createCliente(@Validated @RequestBody ClienteDto clienteDto, HttpServletRequest request) {

        String empresaMoon = request.getHeader("empresa");
        String empresa_2 = (String) request.getSession().getAttribute("token");
        System.out.println("Empresa: " + empresa_2);
        System.out.println("Empresa: " + empresaMoon);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.createCliente(clienteDto, Long.parseLong(empresaMoon)));
    }

    @PostMapping("/getClientById")
    public ResponseEntity<ClienteDto> getClienteById(@RequestBody Long id) {

        return ResponseEntity.ok(clienteService.getClienteById(id));
    }

    @PostMapping("/getAllClient") 
    public ResponseEntity<List<ClienteDto>> getAllClientes(@RequestBody ClienteDto clienteDto) {

        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @PostMapping("/findClientesByEmpresaId")
    public ResponseEntity<List<ClienteDto>> findClientesByEmpresaId(@RequestBody Long id_empresa) {
    
        return ResponseEntity.ok(clienteService.findClientesByEmpresaId(id_empresa));
    }

    @PostMapping("/updateClient")
    public ResponseEntity<ClienteDto> updateCliente(@RequestBody ClienteDto clienteDto) {
        ClienteDto updatedCliente = clienteService.updateCliente(clienteDto);
        return ResponseEntity.ok(updatedCliente);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}