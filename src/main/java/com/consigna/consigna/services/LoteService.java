package com.consigna.consigna.services;

import com.consigna.consigna.dtos.LoteRequestDTO;
import com.consigna.consigna.dtos.LoteResponseDTO;
import com.consigna.consigna.enums.StatusPeca;
import com.consigna.consigna.models.*;
import com.consigna.consigna.repository.ConsignatarioRepository;
import com.consigna.consigna.repository.LoteRepository;
import com.consigna.consigna.repository.PecaRepository;
import com.consigna.consigna.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.consigna.consigna.exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import static com.consigna.consigna.mapper.ObjectMapper.parseObjectsList;
import static com.consigna.consigna.mapper.ObjectMapper.parseObject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public LoteResponseDTO createLoteWithPecas(LoteRequestDTO loteDto) {

        if (loteDto.getPecas() == null || loteDto.getPecas().isEmpty()) {
            throw new IllegalArgumentException("Lote precisa ter pelo menos uma peça.");
        }

        Consignatario consignatario = consignatarioRepository.findById(loteDto.getConsignatarioId())
                .orElseThrow(() -> new IllegalArgumentException("Consignatario não encontrado com o ID: " + loteDto.getConsignatarioId()));

        Usuario usuario = usuarioRepository.findById(loteDto.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado com o ID: " + loteDto.getUsuarioId()));

        Lote loteEntity = new Lote();

        loteEntity.setStatus(StatusPeca.ATIVO.name());
        loteEntity.setDataEntrada(LocalDateTime.now());
        loteEntity.setConsignatario(consignatario);
        loteEntity.setUsuario(usuario);

        List<Peca> pecas = loteDto.getPecas().stream()
                .map(pecaDto -> {
                    Peca peca = new Peca();

                    peca.setDescricao(pecaDto.getDescricao());
                    peca.setQuantidade(pecaDto.getQuantidade());
                    peca.setValorMinimo(pecaDto.getValorMinimo());
                    peca.setStatus(pecaDto.getStatus());
                    peca.setValorDeVenda(pecaDto.getValorDeVenda());
                    peca.setValorDeRepasse(pecaDto.getValorDeRepasse());
                    peca.setDataAlteracaoStatus(pecaDto.getDataAlteracaoStatus());


                    peca.setLote(loteEntity);

                    return peca;
                })
                .collect(Collectors.toList());

        loteEntity.setPecas(pecas);
        Lote savedLote = loteRepository.save(loteEntity);

        return parseObject(savedLote, LoteResponseDTO.class);
    }

    public LoteResponseDTO getById(Long id) {
        var lote = loteRepository.findByIdWithPecas(id).orElseThrow(() -> new ResourceNotFoundException("Lote not found"));
        return parseObject(lote, LoteResponseDTO.class);
    }

    public Page<LoteResponseDTO> getAll(Pageable pageable) {
        return loteRepository.findAllWithPecas(pageable).map(lote -> parseObject(lote, LoteResponseDTO.class));
    }

    @Transactional
    public void delete(Long id) {
        var lote = loteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote not found"));
        loteRepository.delete(lote);
    }

}
