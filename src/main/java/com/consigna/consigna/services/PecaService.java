package com.consigna.consigna.services;

import com.consigna.consigna.dtos.LoteDTO;
import com.consigna.consigna.dtos.PecaDTO;
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
}
