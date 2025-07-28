package com.consigna.consigna.services;

import com.consigna.consigna.dtos.LoteRequest;
import com.consigna.consigna.dtos.LoteResponse;
import com.consigna.consigna.repository.LoteRepository;
import com.consigna.consigna.repository.PecaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoteService {
//
//    private final LoteRepository loteRepository;
//    private final PecaRepository pecaRepository; // para buscar peças depois
//
//    public LoteResponse criarLoteComPecas(LoteRequest request) {
//        Long loteId = loteRepository.inserirLoteComProcedure(request.getCodigo(), request.getDescricao());
//
//        LoteResponse response = new LoteResponse();
//        response.setId(loteId);
//        response.setCodigo(request.getCodigo());
//        response.setDescricao(request.getDescricao());
//        response.setPecas(pecaRepository.listarPecasPorLote(loteId));
//
//        return response;
//    }
//
//    public LoteResponse buscarLotePorId(Long id) {
//        // Aqui você buscaria os dados do Lote + peças
//        LoteResponse response = loteRepository.buscarLoteResponsePorId(id);
//        response.setPecas(pecaRepository.listarPecasPorLote(id));
//        return response;
//    }
//
//    public List<LoteResponse> listarLotes() {
//        List<LoteResponse> lotes = loteRepository.listarLotes();
//        for (LoteResponse lote : lotes) {
//            lote.setPecas(pecaRepository.listarPecasPorLote(lote.getId()));
//        }
//        return lotes;
//    }
}
