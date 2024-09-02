package com.burguer_server.controllers;

import com.burguer_server.payloads.SellerPayloadRequest;
import com.burguer_server.payloads.SellerPayloadResponse;
import com.burguer_server.services.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService service;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid SellerPayloadRequest payloadRequest, UriComponentsBuilder builder) {
        var seller = service.save(payloadRequest);

        var uri = builder.path("/seller/{id}").buildAndExpand(seller.getId()).toUri();

        return ResponseEntity.created(uri).body(new SellerPayloadResponse(seller));
    }
}
