package com.consigna.consigna.services;

import com.consigna.consigna.dtos.ConsignatarioDTO;
import com.consigna.consigna.exceptions.ResourceNotFoundException;
import com.consigna.consigna.models.Consignatario;
import com.consigna.consigna.repository.ConsignatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public ConsignatarioDTO getById(Long id) {
        return parseObject(consignatarioRepository.findById(id), ConsignatarioDTO.class);
    }

    public Page<ConsignatarioDTO> getAll(String searchTerm, Pageable pageable) {
        Page<Consignatario> consignatariosPage;
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            consignatariosPage = consignatarioRepository.findByNomeContainingIgnoreCaseOrDocumentoContainingIgnoreCase(searchTerm, searchTerm, pageable);
        } else {
            consignatariosPage = consignatarioRepository.findAll(pageable);
        }
        return consignatariosPage.map(consignatario -> parseObject(consignatario, ConsignatarioDTO.class));
    }

    public ConsignatarioDTO update(Long id, ConsignatarioDTO dto) {
        var entity = consignatarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consignatario not found with id " + id));

        entity.setNome(dto.getNome());
        entity.setDocumento(dto.getDocumento());
        entity.setTipoDocumento(dto.getTipoDocumento());
        entity.setTelefone(dto.getTelefone());

        return parseObject(consignatarioRepository.save(entity), ConsignatarioDTO.class);
    }

    public void delete(Long id) {
        var entity = consignatarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consignatario not found with id " + id));

        consignatarioRepository.delete(entity);
    }
}
