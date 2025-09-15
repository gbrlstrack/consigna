package com.consigna.consigna.controllers;

import com.consigna.consigna.dtos.CategoriaDTO;
import com.consigna.consigna.dtos.UsuarioDTO;
import com.consigna.consigna.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoriaDTO create(CategoriaDTO categoria) {
        return categoriaService.create(categoria);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoriaDTO getById(@PathVariable Long id) {
        return categoriaService.getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoriaDTO> getAll() {
        return categoriaService.getAll();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoriaDTO update(@PathVariable Long id, @RequestBody CategoriaDTO categoria) {
        return categoriaService.update(id, categoria);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        categoriaService.delete(id);
    }
}
