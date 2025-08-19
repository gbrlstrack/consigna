package com.consigna.consigna.services;

import com.consigna.consigna.dtos.UsuarioDTO;
import com.consigna.consigna.models.Usuario;
import com.consigna.consigna.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.consigna.consigna.mapper.ObjectMapper.parseObject;
import static com.consigna.consigna.mapper.ObjectMapper.parseObjectsList;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioDTO create(UsuarioDTO usuario) {
        var entity = parseObject(usuario, Usuario.class);
        return parseObject(usuarioRepository.save(entity), UsuarioDTO.class);
    }

    public UsuarioDTO getById(Long id) {
        var usuario = usuarioRepository.findById(id);
        return parseObject(usuario, UsuarioDTO.class);
    }

    public List<UsuarioDTO> getAll() {
        return parseObjectsList(usuarioRepository.findAll(), UsuarioDTO.class);
    }
}
