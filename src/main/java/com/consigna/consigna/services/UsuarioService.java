package com.consigna.consigna.services;

import com.consigna.consigna.dtos.UsuarioDTO;
import com.consigna.consigna.models.Usuario;
import com.consigna.consigna.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.consigna.consigna.mapper.ObjectMapper.parseObject;
import static com.consigna.consigna.mapper.ObjectMapper.parseObjectsList;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDTO create(UsuarioDTO usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getSenha());
        var entity = parseObject(usuario, Usuario.class);
        entity.setSenha(encodedPassword);
        return parseObject(usuarioRepository.save(entity), UsuarioDTO.class);
    }

    public UsuarioDTO getById(Long id) {
        var usuario = usuarioRepository.findById(id);
        return parseObject(usuario, UsuarioDTO.class);
    }

    public Page<UsuarioDTO> getAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(user -> parseObject(user, UsuarioDTO.class));
    }

    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));

        usuario.setNome(usuarioDTO.getNome());
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setEmail(usuarioDTO.getEmail());
        if (usuarioDTO.getSenha() != null) usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));


        return parseObject(usuarioRepository.save(usuario), UsuarioDTO.class);
    }

    public void delete(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
        usuarioRepository.delete(usuario);
    }
}
