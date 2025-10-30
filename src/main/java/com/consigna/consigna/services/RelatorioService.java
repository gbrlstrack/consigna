package com.consigna.consigna.services;

import com.consigna.consigna.exceptions.ResourceNotFoundException;
import com.consigna.consigna.models.Consignatario;
import com.consigna.consigna.models.Lote;
import com.consigna.consigna.repository.LoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final LoteRepository loteRepository;
    private final PdfService pdfService;
    private final EmailService emailService;

    @Transactional(readOnly = true)
    public void gerarEEnviarRelatorioUltimoLote() {
        Lote ultimoLote = loteRepository.findFirstByOrderByDataEntradaDesc()
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum lote encontrado."));

        ByteArrayInputStream pdfStream = pdfService.gerarRelatorioLote(ultimoLote);

        Consignatario consignatario = ultimoLote.getConsignatario();
        String emailDestino = consignatario.getEmail();

        if (emailDestino == null || emailDestino.isBlank()) {
            throw new IllegalStateException("O consignatário " + consignatario.getNome() + " não possui um e-mail cadastrado.");
        }

        String nomeArquivo = "relatorio-lote-" + ultimoLote.getId() + ".pdf";
        String assunto = "Relatório do seu último lote #" + ultimoLote.getId();
        String corpo = "Olá, " + consignatario.getNome() + "!<br><br>Segue em anexo o relatório do seu último lote cadastrado em nosso sistema.<br><br>Atenciosamente,<br>Equipe Consigna.";

        emailService.enviarEmailComAnexo(emailDestino, assunto, corpo, pdfStream.readAllBytes(), nomeArquivo);
    }
}