package com.consigna.consigna.services;

import com.consigna.consigna.dtos.LoteRequestDTO;
import com.consigna.consigna.dtos.LoteResponseDTO;
import com.consigna.consigna.enums.StatusPeca;
import com.consigna.consigna.models.*;
import com.consigna.consigna.repository.ConsignatarioRepository;
import com.consigna.consigna.repository.LoteRepository;
import com.consigna.consigna.repository.PecaRepository;
import com.consigna.consigna.repository.UsuarioRepository;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.consigna.consigna.exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static com.consigna.consigna.mapper.ObjectMapper.parseObjectsList;
import static com.consigna.consigna.mapper.ObjectMapper.parseObject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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
    @Autowired
    QRCodeService qrCodeService;

    private final PecaRepository pecaRepository; // para buscar peças depois

    @Transactional
    public LoteResponseDTO createLoteWithPecas(LoteRequestDTO loteDto) {

        if (loteDto.getPecas() == null || loteDto.getPecas().isEmpty()) {
            throw new IllegalArgumentException("Lote precisa ter pelo menos uma peça.");
        }

        Consignatario consignatario = consignatarioRepository.findById(loteDto.getConsignatarioId())
                .orElseThrow(() -> new IllegalArgumentException("Consignatario não encontrado com o ID: " + loteDto.getConsignatarioId()));

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario loggedUser = usuarioRepository.findByLogin(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário '" + userDetails.getUsername() + "' não encontrado no banco de dados."));

        Lote loteEntity = new Lote();

        loteEntity.setStatus(StatusPeca.ATIVO.name());
        loteEntity.setDataEntrada(LocalDateTime.now());
        loteEntity.setConsignatario(consignatario);
        loteEntity.setUsuario(loggedUser);
        loteEntity.setValorTotal(loteDto.getValorTotal());

        List<Peca> pecas = loteDto.getPecas().stream()
                .map(pecaDto -> {
                    Peca peca = new Peca();

                    peca.setDescricao(pecaDto.getDescricao());
                    peca.setQuantidade(pecaDto.getQuantidade());
                    peca.setValorMinimo(pecaDto.getValorMinimo());
                    peca.setStatus(StatusPeca.ATIVO.name());
                    peca.setValorDeVenda(pecaDto.getValorDeVenda());
                    peca.setValorDeRepasse(pecaDto.getValorDeRepasse());
                    peca.setDataAlteracaoStatus(pecaDto.getDataAlteracaoStatus());

                    try {
                        peca.setQrCode(qrCodeService.generateQRCode(UUID.randomUUID().toString()));
                    } catch (WriterException | IOException e) {
                        throw new RuntimeException(e);
                    }


                    peca.setLote(loteEntity);

                    return peca;
                })
                .collect(Collectors.toList());

        Double valorTotal = pecas.stream().mapToDouble(peca -> peca.getValorDeVenda() * peca.getQuantidade()).sum();
        loteEntity.setPecas(pecas);
        loteEntity.setValorTotal(valorTotal);
        Lote savedLote = loteRepository.save(loteEntity);

        return parseObject(savedLote, LoteResponseDTO.class);
    }

    public LoteResponseDTO getById(Long id) {
        var lote = loteRepository.findByIdWithPecas(id).orElseThrow(() -> new ResourceNotFoundException("Lote not found"));
        return parseObject(lote, LoteResponseDTO.class);
    }

    public Page<LoteResponseDTO> getAll(String nomeConsignatario, Pageable pageable) {
        Page<Lote> lotesPage;
        if (nomeConsignatario != null && !nomeConsignatario.trim().isEmpty()) {
            lotesPage = loteRepository.findByConsignatarioNomeContainingIgnoreCase(nomeConsignatario, pageable);
        } else {
            lotesPage = loteRepository.findAllWithPecas(pageable);
        }
        return lotesPage.map(lote -> parseObject(lote, LoteResponseDTO.class));
    }

    @Transactional
    public void delete(Long id) {
        var lote = loteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote not found"));
        loteRepository.delete(lote);
    }

}
