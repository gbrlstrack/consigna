package com.consigna.consigna.controllers;

import com.consigna.consigna.dtos.ConsignatarioDTO;
import com.consigna.consigna.services.ConsignatarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consignatario")
public class ConsignatarioController {

    @Autowired
    ConsignatarioService consignatarioService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ConsignatarioDTO create(@RequestBody ConsignatarioDTO consignatario) {
        return consignatarioService.create(consignatario);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ConsignatarioDTO getById(@PathVariable Long id) {
        return consignatarioService.getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ConsignatarioDTO> getAll(
            @RequestParam(required = false) String searchTerm,
            @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable
    ) {
        return consignatarioService.getAll(searchTerm, pageable);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ConsignatarioDTO update(@PathVariable Long id, @RequestBody ConsignatarioDTO consignatario) {
        return consignatarioService.update(id, consignatario);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consignatarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
