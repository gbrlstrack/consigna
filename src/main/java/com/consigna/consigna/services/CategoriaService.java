package com.consigna.consigna.services;

import com.consigna.consigna.dtos.CategoriaDTO;
import com.consigna.consigna.exceptions.ResourceNotFoundException;
import com.consigna.consigna.models.Categoria;
import com.consigna.consigna.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.consigna.consigna.mapper.ObjectMapper.parseObject;
import static com.consigna.consigna.mapper.ObjectMapper.parseObjectsList;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public CategoriaDTO create(CategoriaDTO categoria) {
        var entity = parseObject(categoria, Categoria.class);
        return parseObject(categoriaRepository.save(entity), CategoriaDTO.class);
    }

    public CategoriaDTO getById(CategoriaDTO categoria) {
        return parseObject(categoriaRepository.findById(categoria.getId()), CategoriaDTO.class);
    }

    public Page<CategoriaDTO> getAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable).map(categoria -> parseObject(categoria, CategoriaDTO.class));
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        var categoria = categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria not found"));

        categoria.setNome(dto.getNome());

        var updated = categoriaRepository.save(categoria);
        return parseObject(updated, CategoriaDTO.class);
    }

    public void delete(CategoriaDTO dto) {
        var categoria = categoriaRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria not found"));
        categoriaRepository.delete(categoria);
    }
}
