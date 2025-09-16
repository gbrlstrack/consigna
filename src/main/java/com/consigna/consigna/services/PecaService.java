package com.consigna.consigna.services;

import com.consigna.consigna.dtos.PecaDTO;
import com.consigna.consigna.dtos.PecaSaidaDTORequest;
import com.consigna.consigna.enums.StatusPeca;
import com.consigna.consigna.exceptions.ResourceNotFoundException;
import com.consigna.consigna.models.Peca;
import com.consigna.consigna.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.consigna.consigna.mapper.ObjectMapper.parseObject;
import static com.consigna.consigna.mapper.ObjectMapper.parseObjectsList;

@Service
public class PecaService {

    @Autowired
    PecaRepository pecaRepository;

    public PecaDTO getById(Long id) {
        var peca = pecaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Peça not found"));
        return parseObject(peca, PecaDTO.class);
    }

    @Transactional
    public List<PecaDTO> getAll() {
        List<Peca> pecas = pecaRepository.findAll();

        return pecas.stream()
                .map(peca -> {
                    PecaDTO dto = new PecaDTO();
                    dto.setId(peca.getId());
                    dto.setDescricao(peca.getDescricao());
                    dto.setQuantidade(peca.getQuantidade());
                    dto.setValorMinimo(peca.getValorMinimo());
                    dto.setStatus(peca.getStatus());
                    dto.setPalavrasChave(peca.getPalavrasChave());
                    dto.setValorDeVenda(peca.getValorDeVenda());
                    dto.setValorDeRepasse(peca.getValorDeRepasse());
                    dto.setDataAlteracaoStatus(peca.getDataAlteracaoStatus());

                    // 💡 Pega o ID do lote e o associa à DTO
                    if (peca.getLote() != null) {
                        dto.setLoteId(peca.getLote().getId());
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<PecaDTO> pecaSaida(List<PecaSaidaDTORequest> request) throws Exception {

        List<PecaDTO> pecasAtualizadas = new ArrayList<>();

        for (PecaSaidaDTORequest pecaSaidaDTO : request) {
            StatusPeca statusEnum = StatusPeca.valueOf(pecaSaidaDTO.getStatus());
            var pecaFromDb = pecaRepository.findById(pecaSaidaDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Peça not found"));

            pecaFromDb.setStatus(statusEnum.name());
            pecaFromDb.setDataAlteracaoStatus(LocalDateTime.now());

            if (statusEnum == StatusPeca.VENDIDO || statusEnum == StatusPeca.RETIRADO_DONO) {
                var canSubtract = pecaFromDb.getQuantidade() - pecaSaidaDTO.getQuantidade() >= 0;
                var isLast = pecaFromDb.getQuantidade() - pecaSaidaDTO.getQuantidade() == 0;
                if (!canSubtract) {
                    throw new Exception("Não há peças suficientes");
                }
                pecaFromDb.setQuantidade(pecaFromDb.getQuantidade() - pecaSaidaDTO.getQuantidade());
                if (isLast) pecaFromDb.setStatus(StatusPeca.INATIVO.name());
            }

            Peca updatedPeca = pecaRepository.save(pecaFromDb);
            pecasAtualizadas.add(parseObject(updatedPeca, PecaDTO.class));
        }

        return pecasAtualizadas;
    }

    public PecaDTO update(Long id, PecaDTO dto) {
        var peca = pecaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Peça not found"));

        peca.setDescricao(dto.getDescricao());
        peca.setValorDeRepasse(dto.getValorDeRepasse());
        peca.setValorMinimo(dto.getValorMinimo());
        peca.setQuantidade(dto.getQuantidade());
        peca.setValorDeVenda(dto.getValorDeVenda());

        var updated = pecaRepository.save(peca);
        return parseObject(updated, PecaDTO.class);
    }

    public void delete(Long id) {
        var peca = pecaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Peça not found"));
        pecaRepository.delete(peca);
    }
}
