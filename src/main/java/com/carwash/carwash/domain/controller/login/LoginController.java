package com.carwash.carwash.domain.controller.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/login")
public class LoginController {
    

    @PostMapping("/logar")
    public String logar() {
        return "Logado";
    }

}
