package com.carwash.carwash.domain.Controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carwash.carwash.util.constantes.Constantes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public void logar() {
        System.out.println(Constantes.EMAIL_VALIDATION_REGEX);
        
    }
}
