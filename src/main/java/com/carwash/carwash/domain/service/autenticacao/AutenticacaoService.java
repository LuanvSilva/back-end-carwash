package com.carwash.carwash.domain.service.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.carwash.carwash.domain.dtos.autenticacao.AuthDto;
import com.carwash.carwash.domain.dtos.autenticacao.TokenResponseDto;
import com.carwash.carwash.domain.service.impl.usuario.UserDetailsServiceImpl;
import com.carwash.carwash.infra.security.JwtUtil;
import com.carwash.carwash.util.exceptions.ErrorMessages;

@Service
public class AutenticacaoService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenResponseDto obterToken(AuthDto authDto) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getSenha());
        authenticationManager.authenticate(authenticationToken);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authDto.getLogin());
        final String token = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        return new TokenResponseDto(token, refreshToken);
    }

    public TokenResponseDto obterRefreshToken(String refreshToken) {
        
        String username = jwtUtil.extractUsername(refreshToken);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtUtil.validateToken(refreshToken, userDetails)) {
            final String newToken = jwtUtil.generateToken(userDetails);
            return new TokenResponseDto(newToken, refreshToken);
        } else {
            throw new IllegalArgumentException(ErrorMessages.TOKEN_INVALIDO);
        }
    }
}
