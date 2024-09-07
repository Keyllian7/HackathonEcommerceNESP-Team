package com.burguer_server.controllers;

import com.burguer_server.payloads.products.ProductsPayloadRequest;
import com.burguer_server.payloads.products.ProductsPayloadResponse;
import com.burguer_server.payloads.stock.StockPayloadRequest;
import com.burguer_server.payloads.stock.StockPayloadResponse;
import com.burguer_server.services.SellerService;
import com.burguer_server.services.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StockService service;

    @Operation(security = { @SecurityRequirement(name = "bearer-key")},
            summary = "Salva Stock no sistema vinculado ao Seller", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto JSON contendo os dados do Stock",
            required = true,
            content = @Content(mediaType = "application/json")),
            responses = {
                    @ApiResponse(description = "Stock salvo com sucesso", responseCode = "201",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
                    @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @PostMapping("/{idSeller}")
    public ResponseEntity save(@PathVariable(name = "idSeller") Long idSeller, @RequestBody @Valid StockPayloadRequest payloadRequest, UriComponentsBuilder builder) {
        var stock = sellerService.saveStockInSeller(idSeller, payloadRequest);

        var uri = builder.path("/stock/{id}").buildAndExpand(stock.getStockId()).toUri();

        return ResponseEntity.created(uri).body(new StockPayloadResponse(stock));
    }

    @Operation(summary = "Retorna uma lista de Stock pelo id do seller",
            responses = {
                    @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
                    @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
                    @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @GetMapping("/stock/{idSeller}")
    public ResponseEntity findStockBySeller(@PathVariable(name = "idSeller") Long idSeller) {
        var stock = sellerService.findStockBySeller(idSeller);

        return ResponseEntity.ok(new StockPayloadResponse(stock));
    }

    @Operation(summary = "Retorna uma lista de Stock",
            responses = {
                    @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
                    @ApiResponse(responseCode = "401", description = "Erro de Autenticação"),
                    @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            })
    @GetMapping("/all")
    public ResponseEntity findAll() {
        var list = service.findAll();

        return ResponseEntity.ok(list.stream().map(StockPayloadResponse::new));
    }




}
