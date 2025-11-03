package com.consigna.consigna.services;

import com.consigna.consigna.dtos.LoginResponseDTO;
import com.consigna.consigna.exceptions.ResourceNotFoundException;
import com.consigna.consigna.models.Usuario;
import com.consigna.consigna.repository.UsuarioRepository;
import com.consigna.consigna.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JavaMailSender mailSender;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void solicitarTokenDeLogin(String login) {
        // Usamos ifPresent para evitar ataques de enumeração de usuário
        usuarioRepository.findByLogin(login).ifPresent(usuario -> {
            String token = UUID.randomUUID().toString();
            usuario.setResetPasswordToken(token);
            usuario.setResetPasswordTokenExpiryDate(LocalDateTime.now().plusMinutes(15)); // Token válido por 15 minutos
            usuarioRepository.save(usuario);

            // A URL deve apontar para a página do seu frontend que vai tratar a validação
            String loginUrl = "http://localhost:5173/login?token=" + token;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@consigna.com");
            message.setTo(usuario.getEmail());
            message.setSubject("Seu Link de Acesso Único");
            message.setText("Para acessar sua conta, clique no link a seguir: " + loginUrl);
            mailSender.send(message);
        });
    }

    @Transactional
    public LoginResponseDTO validarTokenEAutenticar(String token) {
        Usuario usuario = usuarioRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Token de login inválido ou já utilizado."));

        if (usuario.getResetPasswordTokenExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Seu token de login expirou. Por favor, solicite um novo.");
        }

        // Invalida o token para que não possa ser usado novamente
        usuario.setResetPasswordToken(null);
        usuario.setResetPasswordTokenExpiryDate(null);
        usuarioRepository.save(usuario);

        // Gera o token JWT para autenticação
        String jwt = jwtTokenProvider.generateToken(usuario);

        return new LoginResponseDTO(jwt);
    }
}