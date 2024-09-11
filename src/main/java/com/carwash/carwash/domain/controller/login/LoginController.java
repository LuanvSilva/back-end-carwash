package com.carwash.carwash.domain.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carwash.carwash.util.constantes.Constantes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String logar() {
        return Constantes.URL_GET_PAGES_LOGIN; 
        
    }
}
