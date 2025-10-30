package com.consigna.consigna.controllers;

import com.consigna.consigna.services.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @PostMapping("/ultimo-lote/enviar-email")
    public ResponseEntity<String> enviarRelatorioUltimoLote() {
        relatorioService.gerarEEnviarRelatorioUltimoLote();
        return ResponseEntity.ok("Solicitação de envio de relatório para o último lote processada. O e-mail será enviado em breve.");
    }
}