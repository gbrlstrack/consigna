package com.consigna.consigna.controllers;

import com.consigna.consigna.dtos.*;
import com.consigna.consigna.models.Usuario;
import com.consigna.consigna.services.AuthService;
import com.consigna.consigna.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/solicitar-login")
    public ResponseEntity<Void> solicitarLogin(@RequestBody EsqueciSenhaRequestDTO payload) {
        authService.solicitarTokenDeLogin(payload.getLogin());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validar-token")
    public ResponseEntity<LoginResponseDTO> validarToken(@RequestBody ValidarTokenRequestDTO payload) {
        LoginResponseDTO response = authService.validarTokenEAutenticar(payload.getToken());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> createAuthenticationToken(@RequestBody AuthRequestDTO authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getSenha())
        );

        final Usuario user = (Usuario) authentication.getPrincipal();
        final String jwt = tokenService.generateToken(user);

        return ResponseEntity.ok(new AuthResponseDTO(jwt, user.getId()));
    }
}