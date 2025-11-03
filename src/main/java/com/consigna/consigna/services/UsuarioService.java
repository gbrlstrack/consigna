package com.consigna.consigna.services;

import com.consigna.consigna.dtos.UsuarioDTO;
import com.consigna.consigna.exceptions.ResourceNotFoundException;
import com.consigna.consigna.models.Usuario;
import com.consigna.consigna.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.consigna.consigna.mapper.ObjectMapper.parseObject;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;


    public void solicitarRedefinicaoSenha(String login) {
        var usuario = usuarioRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String token = UUID.randomUUID().toString();
        usuario.setResetPasswordToken(token);
        usuario.setResetPasswordTokenExpiryDate(LocalDateTime.now().plusHours(1)); // Token válido por 1 hora

        usuarioRepository.save(usuario);

        String resetUrl = "http://localhost:5173/redefinir-senha?token=" + token; // URL do seu frontend

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@consigna.com");
        message.setTo(usuario.getEmail());
        message.setSubject("Redefinição de Senha");
        message.setText("Para redefinir sua senha, clique no link: " + resetUrl);
        mailSender.send(message);
    }

    public void redefinirSenha(String token, String novaSenha) {
        var usuario = usuarioRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido ou expirado."));

        if (usuario.getResetPasswordTokenExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado.");
        }

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuario.setResetPasswordToken(null);
        usuario.setResetPasswordTokenExpiryDate(null);

        usuarioRepository.save(usuario);
    }

    public UsuarioDTO create(UsuarioDTO usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getSenha());
        var entity = parseObject(usuario, Usuario.class);
        entity.setSenha(encodedPassword);
        var savedEntity = usuarioRepository.save(entity);
        return parseObject(savedEntity, UsuarioDTO.class);
    }

    public UsuarioDTO getById(Long id) {
        var entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
        return parseObject(entity, UsuarioDTO.class);
    }

    public Page<UsuarioDTO> getAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(user -> parseObject(user, UsuarioDTO.class));
    }

    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));

        usuario.setNome(usuarioDTO.getNome());
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setEmail(usuarioDTO.getEmail());
        if (usuarioDTO.getSenha() != null) {
            usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        }


        var updatedUser = usuarioRepository.save(usuario);
        return parseObject(updatedUser, UsuarioDTO.class);
    }

    public void delete(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
        usuarioRepository.delete(usuario);
    }
}
