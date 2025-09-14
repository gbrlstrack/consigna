package com.consigna.consigna.controllers;

import com.consigna.consigna.dtos.ConsignatarioDTO;
import com.consigna.consigna.services.ConsignatarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ConsignatarioDTO> getAll() {
        return consignatarioService.getAll();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ConsignatarioDTO update(@PathVariable Long id, @RequestBody ConsignatarioDTO consignatario) {
        return consignatarioService.update(id, consignatario);
    }

    @DeleteMapping(value = "/id")
    public void delete(@PathVariable Long id) {
        consignatarioService.delete(id);
    }
}
