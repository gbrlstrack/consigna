package com.consigna.consigna.controllers;

import com.consigna.consigna.dtos.LoteDTO;
import com.consigna.consigna.services.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lote")
public class LoteController {

    @Autowired
    private LoteService loteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoteDTO> createLote(@RequestBody LoteDTO request){
        LoteDTO response = loteService.createLoteWithPecas(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    };

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LoteDTO> findAll() {
        return loteService.getAll();
    };

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoteDTO findById(@PathVariable("id") Long id) {
        return loteService.getById(id);
    }
}