package com.consigna.consigna.services;

import com.consigna.consigna.dtos.LoteDTO;
import com.consigna.consigna.models.Lote;
import com.consigna.consigna.repository.LoteRepository;
import com.consigna.consigna.repository.PecaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.consigna.consigna.exceptions.ResourceNotFoundException;

import static com.consigna.consigna.mapper.ObjectMapper.parseObjectsList;
import static com.consigna.consigna.mapper.ObjectMapper.parseObject;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoteService {

    @Autowired
    LoteRepository loteRepository;
    private final PecaRepository pecaRepository; // para buscar peças depois

    public LoteDTO createLoteWithPecas(LoteDTO lote) {
        var entity = parseObject(lote, Lote.class);
        return parseObject(loteRepository.save(entity), LoteDTO.class);
    }

    public LoteDTO getById(Long id) {
        var lote = loteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lote not found"));
        return parseObject(lote, LoteDTO.class);
    }

    public List<LoteDTO> getAll() {
        return parseObjectsList(loteRepository.findAll(), LoteDTO.class);
    }
}
