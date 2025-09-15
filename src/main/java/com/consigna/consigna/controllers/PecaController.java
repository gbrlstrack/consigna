package com.consigna.consigna.controllers;


import com.consigna.consigna.dtos.ConsignatarioDTO;
import com.consigna.consigna.dtos.PecaDTO;
import com.consigna.consigna.services.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peca")
public class PecaController {

    @Autowired
    PecaService pecaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PecaDTO> findAll() {
        return pecaService.getAll();
    }

    ;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PecaDTO findById(@PathVariable Long id) {
        return pecaService.getById(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PecaDTO update(@PathVariable Long id, @RequestBody PecaDTO peca) {
        return pecaService.update(id, peca);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        pecaService.delete(id);
    }
}
