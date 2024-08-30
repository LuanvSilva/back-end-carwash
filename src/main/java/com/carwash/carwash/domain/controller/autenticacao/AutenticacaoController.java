// package com.carwash.carwash.domain.controller.autenticacao;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api/v1/auth")
// public class AutenticacaoController {
    
//     @Autowired
//     private AuthenticationManager authenticationManager;

//     @Autowired
//     private AutenticacaoService autenticacaoService;

//     @PostMapping
//     @ResponseStatus(HttpStatus.OK)
//     public TokenResponseDto auth(@RequestBody AuthDto authDto) {

//         var usuarioAutenticationToken = new UsernamePasswordAuthenticationToken(authDto.login(), authDto.senha());

//         authenticationManager.authenticate(usuarioAutenticationToken);

//         return autenticacaoService.obterToken(authDto);
//     }

//     @PostMapping("/refresh-token")
//     @ResponseStatus(HttpStatus.OK)
//     public TokenResponseDto authRefreshToken(@RequestBody RequestRefreshDto requestRefreshDto) {
//         return autenticacaoService.obterRefreshToken(requestRefreshDto.refreshToken());
//     }
// }
