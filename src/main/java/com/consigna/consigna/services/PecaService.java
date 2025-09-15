package com.consigna.consigna.services;

import com.consigna.consigna.dtos.PecaDTO;
import com.consigna.consigna.dtos.PecaSaidaDTORequest;
import com.consigna.consigna.enums.StatusPeca;
import com.consigna.consigna.exceptions.ResourceNotFoundException;
import com.consigna.consigna.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<PecaDTO> getAll() {
        return parseObjectsList(pecaRepository.findAll(), PecaDTO.class);
    }

    public List<PecaSaidaDTORequest> pecaSaida(List<PecaSaidaDTORequest> request, String status) {
        StatusPeca statusEnum = StatusPeca.valueOf(status);
        request.forEach(peca -> {
            var pecaFromDb = pecaRepository.findById(peca.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Peça not found"));
            peca.setStatus(statusEnum.name());

            if (statusEnum == StatusPeca.VENDIDO) {
                pecaFromDb.setQuantidade(pecaFromDb.getQuantidade() - peca.getQuantidade());
            }
        });

        return request;
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
