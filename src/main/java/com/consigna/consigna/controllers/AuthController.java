package com.consigna.consigna.controllers;

import com.consigna.consigna.dtos.AuthRequestDTO;
import com.consigna.consigna.dtos.AuthResponseDTO;
import com.consigna.consigna.models.Usuario;
import com.consigna.consigna.services.TokenService;
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
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

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