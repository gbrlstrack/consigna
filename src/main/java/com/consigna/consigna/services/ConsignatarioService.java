package com.consigna.consigna.services;

import com.consigna.consigna.dtos.ConsignatarioDTO;
import com.consigna.consigna.exceptions.ResourceNotFoundException;
import com.consigna.consigna.models.Consignatario;
import com.consigna.consigna.repository.ConsignatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.consigna.consigna.mapper.ObjectMapper.parseObject;
import static com.consigna.consigna.mapper.ObjectMapper.parseObjectsList;

@Service
public class ConsignatarioService {

    @Autowired
    ConsignatarioRepository consignatarioRepository;

    public ConsignatarioDTO create(ConsignatarioDTO consignatario) {
        var entity = parseObject(consignatario, Consignatario.class);
        return parseObject(consignatarioRepository.save(entity), ConsignatarioDTO.class);
    }

    public ConsignatarioDTO getById(Long id){
        return parseObject(consignatarioRepository.findById(id), ConsignatarioDTO.class);
    }

    public List<ConsignatarioDTO> getAll(){
        return parseObjectsList(consignatarioRepository.findAll(), ConsignatarioDTO.class);
    }

    public ConsignatarioDTO update(Long id, ConsignatarioDTO dto) {
        var entity = consignatarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consignatario not found with id " + id));

        entity.setNome(dto.getNome());
        entity.setDocumento(dto.getDocumento());
        entity.setTipoDocumento(dto.getDocumento());
        entity.setTelefone(dto.getTelefone());

        return parseObject(consignatarioRepository.save(entity), ConsignatarioDTO.class);
    }

    public void delete(Long id) {
        var entity = consignatarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consignatario not found with id " + id));

        consignatarioRepository.delete(entity);
    }
}
