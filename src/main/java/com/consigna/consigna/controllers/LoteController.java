package com.consigna.consigna.controllers;

import com.consigna.consigna.dtos.LoteRequest;
import com.consigna.consigna.dtos.LoteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lote")
public class LoteController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoteResponse> createLote(@RequestBody LoteRequest request){
        LoteResponse response = loteService.criarLoteComPecas(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    };

}