package com.consigna.consigna.controllers;


import com.consigna.consigna.dtos.ConsignatarioDTO;
import com.consigna.consigna.dtos.PecaDTO;
import com.consigna.consigna.dtos.PecaDTORequest;
import com.consigna.consigna.dtos.PecaSaidaDTORequest;
import com.consigna.consigna.services.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peca") // Sugestão: Usar plural para endpoints de coleção
public class PecaController {

    @Autowired
    PecaService pecaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PecaDTO> findAll(
            @RequestParam(name = "descricao", required = false) String descricao,
            @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        return pecaService.getAll(descricao, pageable);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PecaDTO findById(@PathVariable Long id) {
        return pecaService.getById(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PecaDTO update(@PathVariable Long id, @RequestBody PecaDTORequest peca) {
        return pecaService.update(id, peca);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pecaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/saidas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PecaDTO> saida(@RequestBody List<PecaSaidaDTORequest> listaSaidas) throws Exception {
        return pecaService.pecaSaida(listaSaidas);
    }
}
