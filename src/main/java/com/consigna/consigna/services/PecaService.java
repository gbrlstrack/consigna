package com.consigna.consigna.services;

import com.consigna.consigna.dtos.PecaDTO;
import com.consigna.consigna.dtos.PecaDTORequest;
import com.consigna.consigna.dtos.PecaSaidaDTORequest;
import com.consigna.consigna.enums.StatusPeca;
import com.consigna.consigna.exceptions.ResourceNotFoundException;
import com.consigna.consigna.models.Peca;
import com.consigna.consigna.models.Saida;
import com.consigna.consigna.models.Usuario;
import com.consigna.consigna.repository.PecaRepository;
import com.consigna.consigna.repository.SaidaRepository;
import com.consigna.consigna.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.consigna.consigna.mapper.ObjectMapper.parseObject;

@Service
public class PecaService {

    @Autowired
    PecaRepository pecaRepository;

    @Autowired
    SaidaRepository saidaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public PecaDTO getById(Long id) {
        var peca = pecaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Peça not found"));
        return parseObject(peca, PecaDTO.class);
    }

    @Transactional
    public Page<PecaDTO> getAll(String descricao, Pageable pageable) {
        Page<Peca> pecasPage;
        String statusToExclude = StatusPeca.INATIVO.name();
        if (descricao != null && !descricao.trim().isEmpty()) {
            pecasPage = pecaRepository.findByDescricaoContainingIgnoreCaseAndStatusNot(descricao, statusToExclude, pageable);
        } else {
            pecasPage = pecaRepository.findAllByStatusNot(statusToExclude, pageable);
        }
        // Refatorado para usar o ObjectMapper, deixando o código mais limpo
        return pecasPage.map(peca -> parseObject(peca, PecaDTO.class));
    }

    @Transactional
    public List<PecaDTO> pecaSaida(List<PecaSaidaDTORequest> request) throws Exception {

        List<PecaDTO> pecasAtualizadas = new ArrayList<>();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario loggedUser = usuarioRepository.findByLogin(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário '" + userDetails.getUsername() + "' não encontrado no banco de dados."));

        for (PecaSaidaDTORequest pecaSaidaDTO : request) {
            StatusPeca statusEnum = StatusPeca.valueOf(pecaSaidaDTO.getStatus());
            var pecaFromDb = pecaRepository.findById(pecaSaidaDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Peça not found"));
            pecaFromDb.setDataAlteracaoStatus(LocalDateTime.now());

            if (statusEnum == StatusPeca.VENDIDO || statusEnum == StatusPeca.RETIRADO_DONO) {
                var canSubtract = pecaFromDb.getQuantidade() - pecaSaidaDTO.getQuantidade() >= 0;
                var isLast = pecaFromDb.getQuantidade() - pecaSaidaDTO.getQuantidade() == 0;
                if (!canSubtract) {
                    throw new Exception("Não há peças suficientes");
                }
                pecaFromDb.setQuantidade(pecaFromDb.getQuantidade() - pecaSaidaDTO.getQuantidade());
                if (isLast) pecaFromDb.setStatus(StatusPeca.INATIVO.name());

                Saida saida = new Saida();
                saida.setDataSaida(LocalDate.now());
                saida.setTipo(statusEnum.name());
                saida.setQuantidade(pecaSaidaDTO.getQuantidade());
                saida.setPeca(pecaFromDb);
                saida.setUsuario(loggedUser);
                saidaRepository.save(saida);
            }

            Peca updatedPeca = pecaRepository.save(pecaFromDb);
            pecasAtualizadas.add(parseObject(updatedPeca, PecaDTO.class));
        }

        return pecasAtualizadas;
    }

    public PecaDTO update(Long id, PecaDTORequest dto) {
        var peca = pecaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Peça not found"));

        var status = StatusPeca.valueOf(dto.getStatus());
        peca.setStatus(status.name());
        peca.setDescricao(dto.getDescricao());
        peca.setValorDeRepasse(dto.getValorDeRepasse());
        peca.setValorMinimo(dto.getValorMinimo());
        peca.setQuantidade(dto.getQuantidade());
        peca.setValorDeVenda(dto.getValorDeVenda());
        peca.setDataAlteracaoStatus(LocalDateTime.now());

        var updated = pecaRepository.save(peca);
        return parseObject(updated, PecaDTO.class);
    }

    public void delete(Long id) {
        var peca = pecaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Peça not found"));
        pecaRepository.delete(peca);
    }
}
