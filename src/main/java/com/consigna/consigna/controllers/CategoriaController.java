package com.consigna.consigna.controllers;

import com.consigna.consigna.dtos.CategoriaDTO;
import com.consigna.consigna.dtos.UsuarioDTO;
import com.consigna.consigna.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public Page<CategoriaDTO> getAll(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        return categoriaService.getAll(pageable);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoriaDTO update(@PathVariable Long id, @RequestBody CategoriaDTO categoria) {
        return categoriaService.update(id, categoria);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
