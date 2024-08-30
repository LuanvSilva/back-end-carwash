// package com.carwash.carwash.domain.security;

// import java.io.IOException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// public class SecurityFilter extends OncePerRequestFilter {

//     @Autowired
//     private AutenticacaoService autenticacaoService;

//     @Autowired
//     private UsuarioRepository usuarioRepository;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {

//         String token = extraiTokeHeader(request);

//         if (token != null) {
//             String login = autenticacaoService.validaTokenJwt(token);
//             Usuario usuario = usuarioRepository.findByLogin(login);

//             if (usuario == null) {
//                 throw  new UnauthorizedException("Unauthorizad");
//             }

//             var autentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

//             SecurityContextHolder.getContext().setAuthentication(autentication);
//         }

//         filterChain.doFilter(request, response);
//     }

//     public String extraiTokeHeader(HttpServletRequest request) {
//         var authHeader = request.getHeader("Authorization");

//         if (authHeader == null) {
//             return null;
//         }

//         if (!authHeader.split(" ")[0].equals("Bearer")) {
//             return  null;
//         }

//         return authHeader.split(" ")[1];
//     }
// }
