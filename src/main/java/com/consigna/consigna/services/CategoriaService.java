package com.consigna.consigna.services;

import com.consigna.consigna.dtos.CategoriaDTO;
import com.consigna.consigna.models.Categoria;
import com.consigna.consigna.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public CategoriaDTO getById(Long id) {
        return parseObject(categoriaRepository.findById(id), CategoriaDTO.class);
    }

    public List<CategoriaDTO> getAll() {
        return parseObjectsList(categoriaRepository.findAll(), CategoriaDTO.class);
    }
}
