package com.consigna.consigna.controllers;

import com.consigna.consigna.dtos.EsqueciSenhaRequestDTO;
import com.consigna.consigna.dtos.UsuarioDTO;
import com.consigna.consigna.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/esqueci-senha")
    public ResponseEntity<Void> esqueciSenha(@RequestBody EsqueciSenhaRequestDTO payload) {
        usuarioService.solicitarRedefinicaoSenha(payload.getLogin());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/redefinir-senha")
    public ResponseEntity<Void> redefinirSenha(@RequestBody Map<String, String> payload) {
        usuarioService.redefinirSenha(payload.get("token"), payload.get("novaSenha"));
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO createUser(@RequestBody UsuarioDTO usuario) {
        return usuarioService.create(usuario);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO getById(@PathVariable Long id) {
        return usuarioService.getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UsuarioDTO> getAll(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        return usuarioService.getAll(pageable);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO update(@PathVariable Long id, @RequestBody UsuarioDTO usuario) {
        return usuarioService.update(id, usuario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
