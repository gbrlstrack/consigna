package com.consigna.consigna.controllers;

import com.consigna.consigna.dtos.LoteRequestDTO;
import com.consigna.consigna.dtos.LoteResponseDTO;
import com.consigna.consigna.enums.StatusPeca;
import com.consigna.consigna.services.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/lote") // Sugestão: Usar plural para endpoints de coleção
public class LoteController {

    @Autowired
    private LoteService loteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoteResponseDTO> createLote(@RequestBody LoteRequestDTO request) {
        LoteResponseDTO response = loteService.createLoteWithPecas(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<LoteResponseDTO> findAll(
            @RequestParam(name = "consignatario", required = false) String nomeConsignatario,
            @RequestParam(required = false) StatusPeca status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataEntradaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataEntradaFim,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataSaidaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataSaidaFim,
            @PageableDefault(page = 0, size = 10, sort = "dataEntrada", direction = org.springframework.data.domain.Sort.Direction.DESC
            ) Pageable pageable) {
        return loteService.getAll(nomeConsignatario, status, dataEntradaInicio, dataEntradaFim, dataSaidaInicio, dataSaidaFim, pageable);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoteResponseDTO findById(@PathVariable("id") Long id) {
        return loteService.getById(id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") Long id) {
        loteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}