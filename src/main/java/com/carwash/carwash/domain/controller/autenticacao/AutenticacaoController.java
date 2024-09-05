package com.carwash.carwash.domain.controller.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carwash.carwash.domain.dtos.autenticacao.AuthDto;
import com.carwash.carwash.domain.dtos.autenticacao.RequestRefreshDto;
import com.carwash.carwash.domain.dtos.autenticacao.TokenResponseDto;
import com.carwash.carwash.domain.service.autenticacao.AutenticacaoService;

@RestController
@RequestMapping("/api/v1/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody AuthDto authDto) {

        try {
            TokenResponseDto tokenResponse = autenticacaoService.obterToken(authDto);
            return ResponseEntity.ok(tokenResponse);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refresh(@RequestBody RequestRefreshDto request) {
        
        try {
            TokenResponseDto tokenResponse = autenticacaoService.obterRefreshToken(request.refreshToken());
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}