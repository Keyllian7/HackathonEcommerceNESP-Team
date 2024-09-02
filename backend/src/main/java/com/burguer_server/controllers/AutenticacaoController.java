package com.burguer_server.controllers;

import com.burguer_server.infra.security.TokenService;
import com.burguer_server.model.user.User;
import com.burguer_server.payloads.auth.DadosAdmin;
import com.burguer_server.payloads.auth.DadosAutenticacao;
import com.burguer_server.payloads.auth.DadosToken;
import com.burguer_server.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Faz login do user no sistema",
            responses = {
                    @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "Não foi encontrado o seller"),
                    @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
                    @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
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

    @Operation(summary = "Salva user no sistema",
            responses = {
                    @ApiResponse(description = "User salvo com sucesso", responseCode = "201"),
                    @ApiResponse(responseCode = "404", description = "Não foi encontrado o seller"),
                    @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
                    @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @PostMapping("/register")
    public ResponseEntity efetuarCadastro(@RequestBody @Valid DadosAutenticacao dadosAutenticacao, UriComponentsBuilder uriBuilder){

        var user = service.save(new User(dadosAutenticacao));
        var uri = uriBuilder.path("/admin/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosAdmin(user));

    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Retorna lista de users",
            responses = {
                    @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "Não foi encontrado o seller"),
                    @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
                    @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/all")
    public ResponseEntity findAllUsers() {
        var list = service.findAll();
        return ResponseEntity.ok(list.stream().map(DadosAdmin::new));
    }
}
