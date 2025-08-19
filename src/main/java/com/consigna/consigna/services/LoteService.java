package com.consigna.consigna.services;

import com.consigna.consigna.dtos.LoteDTO;
import com.consigna.consigna.models.Consignatario;
import com.consigna.consigna.models.Lote;
import com.consigna.consigna.models.Usuario;
import com.consigna.consigna.repository.ConsignatarioRepository;
import com.consigna.consigna.repository.LoteRepository;
import com.consigna.consigna.repository.PecaRepository;
import com.consigna.consigna.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.consigna.consigna.exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import static com.consigna.consigna.mapper.ObjectMapper.parseObjectsList;
import static com.consigna.consigna.mapper.ObjectMapper.parseObject;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoteService {

    @Autowired
    LoteRepository loteRepository;
    @Autowired
    ConsignatarioRepository consignatarioRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    private final PecaRepository pecaRepository; // para buscar peças depois

    @Transactional
    public LoteDTO createLoteWithPecas(LoteDTO lote) {

        if (lote.getPecas() == null || lote.getPecas().isEmpty()) {
            throw new IllegalArgumentException("Lote precisa ter pelo menos uma peça.");
        }

        Consignatario consignatario = consignatarioRepository.findById(lote.getConsignatarioId())
                .orElseThrow(() -> new IllegalArgumentException("Consignatario não encontrado com o ID: " + lote.getConsignatarioId()));

        Usuario usuario = usuarioRepository.findById(lote.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado com o ID: " + lote.getUsuarioId()));

        var entity = parseObject(lote, Lote.class);
        entity.setConsignatario(consignatario);
        entity.setUsuario(usuario);
        System.out.println(entity);
        System.out.println("Lote: " + lote);
        return parseObject(loteRepository.save(entity), LoteDTO.class);
    }

    public LoteDTO getById(Long id) {
        var lote = loteRepository.findByIdWithPecas(id).orElseThrow(() -> new ResourceNotFoundException("Lote not found"));
        return parseObject(lote, LoteDTO.class);
    }

    public List<LoteDTO> getAll() {
        return parseObjectsList(loteRepository.findAllWithPecas(), LoteDTO.class);
    }
}
