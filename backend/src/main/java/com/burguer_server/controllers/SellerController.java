package com.burguer_server.controllers;

import com.burguer_server.infra.security.TokenService;
import com.burguer_server.model.user.User;
import com.burguer_server.payloads.auth.DadosAutenticacao;
import com.burguer_server.payloads.auth.DadosToken;
import com.burguer_server.payloads.seller.SellerPayloadRequest;
import com.burguer_server.payloads.seller.SellerPayloadResponse;
import com.burguer_server.services.SellerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SellerService service;

    @Operation(summary = "Faz login do seller no sistema", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto JSON contendo os dados do Seller",
            required = true,
            content = @Content(mediaType = "application/json")),
            responses = {
                    @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
                    @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
                    @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        try {
            System.out.println("Recebendo dados de autenticação: " + dados.email());
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
            var authentication = manager.authenticate(authenticationToken);
            System.out.println(authentication.getPrincipal());
            var tokenJWT = tokenService.geraToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new DadosToken(tokenJWT));

        } catch (Exception e) {
            e.printStackTrace(); // Adicionado para depuração
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "Salva seller no sistema",  requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto JSON contendo os dados do Seller",
            required = true,
            content = @Content(mediaType = "application/json")),
            responses = {
            @ApiResponse(description = "Seller salvo com sucesso", responseCode = "201",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o seller"),
            @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid SellerPayloadRequest payloadRequest, UriComponentsBuilder builder) {
        var seller = service.save(payloadRequest);

        var uri = builder.path("/seller/{id}").buildAndExpand(seller.getId()).toUri();

        return ResponseEntity.created(uri).body(new SellerPayloadResponse(seller));
    }

    @Operation(summary = "Retorna uma lista de sellers",
            responses = {
                    @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
                    @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
                    @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @GetMapping("/all")
    public ResponseEntity findAll() {
        var list = service.findAll();

        return ResponseEntity.ok(list.stream().map(SellerPayloadResponse::new));
    }

    @Operation(summary = "Retorna seller pelo id",
            responses = {
                    @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Não foi encontrado o seller"),
                    @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
                    @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable (name = "id") Long id) {
        try {
            var seller = service.findById(id);

            return ResponseEntity.ok(new SellerPayloadResponse(seller));

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Procura projetos pelo id, que está vinculado com o id do usuário",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto JSON contendo os dados do Seller",
                    required = true,
                    content = @Content(mediaType = "application/json")),
            responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o seller"),
            @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity update (@PathVariable Long id, @RequestBody @Valid SellerPayloadRequest payload) {
        try {
            var seller = service.update(id, payload);
            return ResponseEntity.ok(new SellerPayloadResponse(seller));

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Deleta sellers pelo id",  responses = {
            @ApiResponse(description = "Seller Deletado com sucesso", responseCode = "204"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o seller"),
            @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            service.deleteById(id);

            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }
}
