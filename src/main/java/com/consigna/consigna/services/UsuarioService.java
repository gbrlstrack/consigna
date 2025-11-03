package com.consigna.consigna.services;

import com.consigna.consigna.dtos.UsuarioDTO;
import com.consigna.consigna.models.Usuario;
import com.consigna.consigna.repository.UsuarioRepository;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;

    public UsuarioDTO create(UsuarioDTO usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getSenha());
        var entity = mapper.map(usuario, Usuario.class);
        entity.setSenha(encodedPassword);
        var savedEntity = usuarioRepository.save(entity);
        return mapper.map(savedEntity, UsuarioDTO.class);
    }

    public UsuarioDTO getById(Long id) {
        var usuario = usuarioRepository.findById(id);
        return mapper.map(usuario, UsuarioDTO.class);
    }

    public Page<UsuarioDTO> getAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(user -> mapper.map(user, UsuarioDTO.class));
    }

    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));

        usuario.setNome(usuarioDTO.getNome());
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setEmail(usuarioDTO.getEmail());
        if (usuarioDTO.getSenha() != null) usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));


        var updatedUser = usuarioRepository.save(usuario);
        return mapper.map(updatedUser, UsuarioDTO.class);
    }

    public void delete(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
        usuarioRepository.delete(usuario);
    }
}
