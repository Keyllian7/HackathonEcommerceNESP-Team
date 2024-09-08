package com.burguer_server.controllers;

import com.burguer_server.infra.security.TokenService;
import com.burguer_server.model.order.OrderItem;
import com.burguer_server.model.user.User;
import com.burguer_server.payloads.auth.DadosAutenticacao;
import com.burguer_server.payloads.auth.DadosToken;
import com.burguer_server.payloads.buyer.BuyerPayloadRequest;
import com.burguer_server.payloads.buyer.BuyerPayloadResponse;
import com.burguer_server.payloads.order.OrderPayloadRequest;
import com.burguer_server.payloads.order.OrderPayloadResponse;
import com.burguer_server.payloads.orderitem.OrderItemPayloadResponse;
import com.burguer_server.payloads.products.ProductsPayloadRequest;
import com.burguer_server.payloads.products.ProductsPayloadResponse;
import com.burguer_server.services.BuyerService;
import com.burguer_server.services.OrderItemService;
import com.burguer_server.services.OrderService;
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

import java.util.List;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BuyerService service;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Operation(summary = "Faz login do Buyer no sistema", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto JSON contendo os dados do Buyer",
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

    @Operation(summary = "Salva Buyer no sistema",  requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto JSON contendo os dados do Buyer",
            required = true,
            content = @Content(mediaType = "application/json")),
            responses = {
            @ApiResponse(description = "Buyer salvo com sucesso", responseCode = "201",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o Buyer"),
            @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid BuyerPayloadRequest payloadRequest, UriComponentsBuilder builder) {
        var Buyer = service.save(payloadRequest);

        var uri = builder.path("/Buyer/{id}").buildAndExpand(Buyer.getId()).toUri();

        return ResponseEntity.created(uri).body(new BuyerPayloadResponse(Buyer));
    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key")},
            summary = "Retorna uma lista de Buyers",
            responses = {
                    @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
                    @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
                    @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @GetMapping("/all")
    public ResponseEntity findAll() {
        var list = service.findAll();

        return ResponseEntity.ok(list.stream().map(BuyerPayloadResponse::new));
    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key")},
            summary = "Retorna Buyer pelo id",
            responses = {
                    @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Não foi encontrado o Buyer"),
                    @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
                    @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable (name = "id") Long id) {
        try {
            var Buyer = service.findById(id);

            return ResponseEntity.ok(new BuyerPayloadResponse(Buyer));

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Atualiza Buyer",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto JSON contendo os dados do Buyer",
                    required = true,
                    content = @Content(mediaType = "application/json")),
            responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o Buyer"),
            @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity update (@PathVariable Long id, @RequestBody @Valid BuyerPayloadRequest payload) {
        try {
            var Buyer = service.update(id, payload);
            return ResponseEntity.ok(new BuyerPayloadResponse(Buyer));

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Deleta Buyers pelo id",  responses = {
            @ApiResponse(description = "Buyer Deletado com sucesso", responseCode = "204"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o Buyer"),
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

    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Retorna lista de pedido de um Buyer",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "204"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o Buyer"),
            @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/cart/{idBuyer}")
    public ResponseEntity<List<OrderItemPayloadResponse>> getOrderItemsByBuyerId(@PathVariable(name = "idBuyergi") Long buyerId) {
        List<OrderItem> orderItems = orderItemService.findAllByBuyer(buyerId);

        // Convertendo OrderItems para OrderItemPayloadResponse
        List<OrderItemPayloadResponse> response = orderItems.stream()
                .map(orderItem -> new OrderItemPayloadResponse(
                        orderItem.getOrderItemId(),
                        orderItem.getProduct(),
                        orderItem.getQuantity(),
                        orderItem.getNotes()
                )).toList();

        return ResponseEntity.ok(response);
    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Salva pedido do buyer",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o Buyer"),
            @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/order/{idBuyer}")
    public ResponseEntity createOrder(@PathVariable (name = "idBuyer") Long idBuyer, @RequestBody @Valid OrderPayloadRequest payloadRequest) {
        var order = service.createOrder(idBuyer, payloadRequest);

        return ResponseEntity.ok().body(new OrderPayloadResponse(order));
    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Salva produto no pedido do buyer",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado o Buyer"),
            @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/order/product/{idBuyer}")
    public ResponseEntity saveProductInOrder(@PathVariable (name = "idBuyer") Long idBuyer, @RequestBody @Valid ProductsPayloadRequest payloadRequest) {
        var product = service.saveProductInOrder(idBuyer, payloadRequest);

        return ResponseEntity.ok().body(new ProductsPayloadResponse(product));
    }


}
