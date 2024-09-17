package br.com.fiap.workmatch.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.workmatch.auth.dto.Credentials;
import br.com.fiap.workmatch.auth.dto.Token;


@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials){
        return authService.login(credentials);
    }

}
