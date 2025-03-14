package com.carwash.carwash.domain.Service.autenticacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.carwash.carwash.domain.Dtos.autenticacao.AuthDto;
import com.carwash.carwash.domain.Dtos.autenticacao.TokenResponseDto;
import com.carwash.carwash.domain.Dtos.cliente.ClienteEmpresaDto;
import com.carwash.carwash.domain.Service.Impl.usuario.UserDetailsServiceImpl;
import com.carwash.carwash.domain.Service.cliente.ClienteService;
import com.carwash.carwash.infra.security.JwtUtil;
import com.carwash.carwash.util.exceptions.ErrorMessages;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class AutenticacaoService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenResponseDto obterToken(AuthDto authDto, HttpServletRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getPassword());
        authenticationManager.authenticate(authenticationToken);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authDto.getLogin());
        final String token = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        ClienteEmpresaDto clienteEmpresa = clienteService.findClienteAndEmpresa(authDto.getLogin());

        HttpSession session = request.getSession(true);
        session.setAttribute("token", token);
        session.setAttribute("refreshToken", refreshToken);


        if (clienteEmpresa != null) {
            session.setAttribute("empresa", clienteEmpresa.getEmpresaMoon());
            session.setAttribute("email", clienteEmpresa.getUserEmail());
            session.setAttribute("userId", clienteEmpresa.getUserId());
        }

        System.out.println("Empresa: " + request.getSession().getAttribute("empresa"));
        System.out.println("Email: " + request.getSession().getAttribute("email"));
        System.out.println("UserId: " + request.getSession().getAttribute("userId"));
        return new TokenResponseDto(token, refreshToken, clienteEmpresa.getEmpresaMoon(), clienteEmpresa.getUserEmail(), clienteEmpresa.getUserId());
    }

    public TokenResponseDto obterRefreshToken(String refreshToken, HttpServletRequest request) {
        String username = jwtUtil.extractUsername(refreshToken);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtUtil.validateToken(refreshToken, userDetails)) {
            final String newToken = jwtUtil.generateToken(userDetails);

            HttpSession session = request.getSession(true);
            session.setAttribute("token", newToken);
            session.setAttribute("refreshToken", refreshToken);

            return new TokenResponseDto(newToken, refreshToken, (Long) session.getAttribute("empresa"), (String) session.getAttribute("email"), (Long) session.getAttribute("userId"));
        } else {
            throw new IllegalArgumentException(ErrorMessages.INVALID_TOKEN);
        }
    }
}
