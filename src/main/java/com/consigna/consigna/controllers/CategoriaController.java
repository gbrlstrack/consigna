package com.consigna.consigna.controllers;

import com.consigna.consigna.dtos.CategoriaDTO;
import com.consigna.consigna.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaDTO categoria) {
        var createdCategoria = categoriaService.create(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdCategoria.getId()).toUri();
        return ResponseEntity.created(uri).body(createdCategoria);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.getById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CategoriaDTO>> getAll(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        return ResponseEntity.ok(categoriaService.getAll(pageable));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO categoria) {
        return ResponseEntity.ok(categoriaService.update(id, categoria));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
