package com.carwash.carwash.domain.Service.solicitacao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carwash.carwash.domain.Dtos.solicitacao.SolicitacaoDTO;
import com.carwash.carwash.domain.Entities.solicitacao.Solicitacao;
import com.carwash.carwash.domain.Entities.status.Status;
import com.carwash.carwash.domain.Repositories.cliente.ClienteRepository;
import com.carwash.carwash.domain.Repositories.empresa.EmpresaRepository;
import com.carwash.carwash.domain.Repositories.item.ItemRepository;
import com.carwash.carwash.domain.Repositories.solicitacao.SolicitacaoRepository;
import com.carwash.carwash.domain.Repositories.status.StatusRepository;
import com.carwash.carwash.util.exceptions.CustomException;
import com.carwash.carwash.util.exceptions.ErrorMessages;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final StatusRepository statusRepository;
    private final EmpresaRepository empresaRepository;
    private final ItemRepository itemRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
    public SolicitacaoDTO createSolicitacao(SolicitacaoDTO solicitacaoDTO, Long empresaId) {

        Solicitacao solicitacao = mapToEntity(solicitacaoDTO, empresaId);
        solicitacao = solicitacaoRepository.save(solicitacao);
        return mapToDto(solicitacao);
    }

    @Transactional(readOnly = true)
    public SolicitacaoDTO getSolicitacaoById(Long id) {

        Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorMessages.REQUEST_NOT_FOUND, HttpStatus.NOT_FOUND));
        return mapToDto(solicitacao);
    }

    @Transactional(readOnly = true)
    public List<SolicitacaoDTO> getAllSolicitacoes() {
        return solicitacaoRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Transactional
    public SolicitacaoDTO updateSolicitacao(SolicitacaoDTO solicitacaoDTO) {

        Solicitacao solicitacao = solicitacaoRepository.findById(solicitacaoDTO.getId())
                .orElseThrow(() -> new CustomException(ErrorMessages.REQUEST_NOT_FOUND, HttpStatus.NOT_FOUND));
        updateEntityFromDto(solicitacaoDTO, solicitacao);
        solicitacao = solicitacaoRepository.save(solicitacao);
        return mapToDto(solicitacao);
    }

    @Transactional
    public void deleteSolicitacao(Long id) {
        solicitacaoRepository.deleteById(id);
    }

    @Transactional
    public SolicitacaoDTO changeStatus(Long solicitacaoId, Long statusId) {

        Solicitacao solicitacao = solicitacaoRepository.findById(solicitacaoId)
                .orElseThrow(() -> new CustomException(ErrorMessages.REQUEST_NOT_FOUND, HttpStatus.NOT_FOUND));
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new CustomException(ErrorMessages.STATUS_NOT_FOUND, HttpStatus.NOT_FOUND));
        solicitacao.setStatus(status);
        solicitacao = solicitacaoRepository.save(solicitacao);
        return mapToDto(solicitacao);
    }

    private Solicitacao mapToEntity(SolicitacaoDTO solicitacaoDTO, Long empresaId) {

        return Solicitacao.builder()
                .empresa(empresaRepository.findById(empresaId)
                        .orElseThrow(() -> new CustomException(ErrorMessages.COMPANY_NOT_FOUND, HttpStatus.NOT_FOUND)))
                .cliente(clienteRepository.findById(solicitacaoDTO.getClienteId())
                        .orElseThrow(() -> new CustomException(ErrorMessages.CLIENT_NOT_FOUND, HttpStatus.NOT_FOUND)))
                .item(itemRepository.findById(solicitacaoDTO.getItemId())
                        .orElseThrow(() -> new CustomException(ErrorMessages.ITEM_NOT_FOUND, HttpStatus.NOT_FOUND)))
                .servico(itemRepository.findById(solicitacaoDTO.getServicoId())
                        .orElseThrow(() -> new CustomException(ErrorMessages.SERVICE_NOT_FOUND, HttpStatus.NOT_FOUND)))
                .status(statusRepository.findById(solicitacaoDTO.getStatusId())
                        .orElseThrow(() -> new CustomException(ErrorMessages.STATUS_NOT_FOUND, HttpStatus.NOT_FOUND)))
                .dataHoraFim(solicitacaoDTO.getDataHoraFim())
                .dataHoraInicio(solicitacaoDTO.getDataHoraInicio())
                .dataAlteracao(solicitacaoDTO.getDataAlteracao())
                .dataCriacao(solicitacaoDTO.getDataCriacao())
                .urgente(solicitacaoDTO.getUrgente())
                .observacao(solicitacaoDTO.getObservacao())
                .valor(solicitacaoDTO.getValor())
                .build();
    }

    private SolicitacaoDTO mapToDto(Solicitacao solicitacao) {

        return SolicitacaoDTO.builder()
                .id(solicitacao.getId())
                .empresaId(solicitacao.getEmpresa().getId())
                .clienteId(solicitacao.getCliente().getId())
                .itemId(solicitacao.getItem().getId())
                .servicoId(solicitacao.getServico().getId())
                .statusId(solicitacao.getStatus().getId())
                .dataHoraFim(solicitacao.getDataHoraFim())
                .dataHoraInicio(solicitacao.getDataHoraInicio())
                .dataAlteracao(solicitacao.getDataAlteracao())
                .dataCriacao(solicitacao.getDataCriacao())
                .urgente(solicitacao.getUrgente())
                .observacao(solicitacao.getObservacao())
                .valor(solicitacao.getValor())
                .build();
    }

    private void updateEntityFromDto(SolicitacaoDTO solicitacaoDTO, Solicitacao solicitacao) {
        
        solicitacao.setCliente(clienteRepository.findById(solicitacaoDTO.getClienteId())
                .orElseThrow(() -> new CustomException(ErrorMessages.CLIENT_NOT_FOUND, HttpStatus.NOT_FOUND)));
        solicitacao.setItem(itemRepository.findById(solicitacaoDTO.getItemId())
                .orElseThrow(() -> new CustomException(ErrorMessages.ITEM_NOT_FOUND, HttpStatus.NOT_FOUND)));
        solicitacao.setServico(itemRepository.findById(solicitacaoDTO.getServicoId())
                .orElseThrow(() -> new CustomException(ErrorMessages.SERVICE_NOT_FOUND, HttpStatus.NOT_FOUND)));
        solicitacao.setStatus(statusRepository.findById(solicitacaoDTO.getStatusId())
                .orElseThrow(() -> new CustomException(ErrorMessages.STATUS_NOT_FOUND, HttpStatus.NOT_FOUND)));
        solicitacao.setDataHoraFim(solicitacaoDTO.getDataHoraFim());
        solicitacao.setDataHoraInicio(solicitacaoDTO.getDataHoraInicio());
        solicitacao.setDataAlteracao(solicitacaoDTO.getDataAlteracao());
        solicitacao.setDataCriacao(solicitacaoDTO.getDataCriacao());
        solicitacao.setUrgente(solicitacaoDTO.getUrgente());
        solicitacao.setObservacao(solicitacaoDTO.getObservacao());
        solicitacao.setValor(solicitacaoDTO.getValor());
    }
}