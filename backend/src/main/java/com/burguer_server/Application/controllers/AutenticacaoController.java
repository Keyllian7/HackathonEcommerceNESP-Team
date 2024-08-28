package com.burguer_server.Application.controllers;

import com.burguer_server.Application.infra.security.TokenService;
import com.burguer_server.Application.model.user.User;
import com.burguer_server.Application.payloads.auth.DadosAdmin;
import com.burguer_server.Application.payloads.auth.DadosAutenticacao;
import com.burguer_server.Application.payloads.auth.DadosToken;
import com.burguer_server.Application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
    try {
        System.out.println("Recebendo dados de autenticação: " + dados.email());
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
        var authentication = manager.authenticate(authenticationToken);
        System.out.println(authentication.getPrincipal());
        var tokenJWT = tokenService.geraToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosToken(tokenJWT));

    }   catch (Exception e) {
        e.printStackTrace(); // Adicionado para depuração
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    }

    @PostMapping("/register")
    public ResponseEntity efetuarCadastro(@RequestBody @Valid DadosAutenticacao dadosAutenticacao, UriComponentsBuilder uriBuilder){

        var user = service.save(new User(dadosAutenticacao));
        var uri = uriBuilder.path("/admin/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosAdmin(user));

    }

    @GetMapping("/all")
    public ResponseEntity findAllUsers() {
        var list = service.findAll();
        return ResponseEntity.ok(list.stream().map(DadosAdmin::new));
    }
}
