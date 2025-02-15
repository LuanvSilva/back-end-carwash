package com.carwash.carwash.domain.Controller.solicitacao;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.carwash.carwash.domain.Dtos.solicitacao.SolicitacaoDTO;
import com.carwash.carwash.domain.Service.solicitacao.SolicitacaoService;
import com.carwash.carwash.util.exceptions.CustomException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/solicitacao")
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    @PostMapping("/create")
    public ResponseEntity<SolicitacaoDTO> createSolicitacao(@Validated @RequestBody SolicitacaoDTO solicitacaoDTO, HttpServletRequest request) {
        String empresaId = request.getHeader("empresa");
        SolicitacaoDTO createdSolicitacao = solicitacaoService.createSolicitacao(solicitacaoDTO, Long.parseLong(empresaId));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSolicitacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoDTO> getSolicitacaoById(@PathVariable Long id) {
        SolicitacaoDTO solicitacaoDTO = solicitacaoService.getSolicitacaoById(id);
        return ResponseEntity.ok(solicitacaoDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SolicitacaoDTO>> getAllSolicitacoes() {
        List<SolicitacaoDTO> solicitacoes = solicitacaoService.getAllSolicitacoes();
        return ResponseEntity.ok(solicitacoes);
    }

    @PutMapping("/update")
    public ResponseEntity<SolicitacaoDTO> updateSolicitacao(@Validated @RequestBody SolicitacaoDTO solicitacaoDTO) {
        SolicitacaoDTO updatedSolicitacao = solicitacaoService.updateSolicitacao(solicitacaoDTO);
        return ResponseEntity.ok(updatedSolicitacao);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSolicitacao(@PathVariable Long id) {
        solicitacaoService.deleteSolicitacao(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/changeStatus/{solicitacaoId}/{statusId}")
    public ResponseEntity<SolicitacaoDTO> changeStatus(@PathVariable Long solicitacaoId, @PathVariable Long statusId) {
        SolicitacaoDTO updatedSolicitacao = solicitacaoService.changeStatus(solicitacaoId, statusId);
        return ResponseEntity.ok(updatedSolicitacao);
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
