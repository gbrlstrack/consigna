package com.consigna.consigna.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void enviarEmailComAnexo(String para, String assunto, String corpo, byte[] anexo, String nomeAnexo) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(para);
            helper.setSubject(assunto);
            helper.setText(corpo, true); // true para permitir HTML
            helper.addAttachment(nomeAnexo, new ByteArrayResource(anexo));

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Trate a exceção (log, etc.)
            throw new RuntimeException("Erro ao enviar e-mail com anexo", e);
        }
    }
}