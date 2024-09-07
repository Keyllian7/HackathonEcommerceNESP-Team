package com.burguer_server.controllers;

import com.burguer_server.model.enums.UserRole;
import com.burguer_server.model.user.Adress;
import com.burguer_server.model.user.Buyer;
import com.burguer_server.payloads.buyer.BuyerPayloadRequest;
import com.burguer_server.payloads.buyer.BuyerPayloadResponse;
import com.burguer_server.payloads.products.ProductsPayloadRequest;
import com.burguer_server.payloads.products.ProductsPayloadResponse;
import com.burguer_server.repositories.BuyerRepository;
import com.burguer_server.services.AdressService;
import com.burguer_server.services.BuyerService;
import com.burguer_server.services.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class BuyerControllerTest {

    @Autowired
    private JacksonTester<BuyerPayloadRequest> buyerPayloadRequest;

    @Autowired
    private JacksonTester<BuyerPayloadResponse> buyerPayloadResponse;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BuyerService buyerService;

    @MockBean
    private AdressService adressService;

    @MockBean
    private BuyerRepository repository;


    @Test
    @DisplayName("Salva Buyer")
    @WithMockUser(username = "user", roles = {"BUYER"})
    void save() throws Exception {
        var buyer = new Buyer(1l, "valdir@gmail.com", "123", UserRole.BUYER, null, "85 99701420", null );
        var adress = new Adress(2l, "57899000", "rua do java", "javacity", 12, "casa", "proximo ao javascript");

        buyer.setBuyerAdress(adress);

        var buyerPayload = new BuyerPayloadRequest(buyer);
        when(adressService.save(adress)).thenReturn(adress);
        when(buyerService.save(buyerPayload)).thenReturn(buyer);

        var response = mvc.perform(post("/buyer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        buyerPayloadRequest.write(
                                new BuyerPayloadRequest(buyer)
                        ).getJson()
                )).andReturn().getResponse();


        var jsonEsperado = buyerPayloadResponse.write(
                new BuyerPayloadResponse(buyer)
        ).getJson();

        Assertions.assertEquals(201, response.getStatus());
        Assertions.assertEquals(jsonEsperado, response.getContentAsString());

    }

    @Test
    @DisplayName("Deleta Buyer")
    @WithMockUser(username = "user", roles = {"BUYER"})
    void delete() throws Exception {

        var buyer = new Buyer(1l, "valdir@gmail.com", "123", UserRole.BUYER, null, "85 99701420", null );
        var adress = new Adress(2l, "57899000", "rua do java", "javacity", 12, "casa", "proximo ao javascript");

        buyer.setBuyerAdress(adress);

        var buyerPayload = new BuyerPayloadRequest(buyer);
        when(adressService.save(adress)).thenReturn(adress);
        when(buyerService.save(buyerPayload)).thenReturn(buyer);
        
        doNothing().when(buyerService).deleteById(1l);

        var response = mvc.perform(MockMvcRequestBuilders.delete("/buyer/" + 1l)
                ).andReturn().getResponse();

        Assertions.assertEquals(204, response.getStatus());
        
    }

    @Test
    @DisplayName("Atualiza Buyer")
    @WithMockUser(username = "user", roles = {"BUYER"})
    void update() throws Exception {

        var buyer = new Buyer(1l, "valdir@gmail.com", "123", UserRole.BUYER, null, "85 99701420", null );
        var adress = new Adress(2l, "57899000", "rua do java", "javacity", 12, "casa", "proximo ao javascript");

        buyer.setBuyerAdress(adress);

        var buyerPayload = new BuyerPayloadRequest(buyer);
        when(adressService.save(adress)).thenReturn(adress);
        when(buyerService.save(buyerPayload)).thenReturn(buyer);

        var buyerAtualizado = new Buyer(2l, "valdirDoJava@gmail.com", "123", UserRole.BUYER, null, "85 99701420", null );

        when(buyerService.update(1l, buyerPayload)).thenReturn(buyer);

        var response = mvc.perform(put("/buyer/" + 1l)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        buyerPayloadRequest.write(
                                new BuyerPayloadRequest(buyer)
                        ).getJson()
                )).andReturn().getResponse();


        var jsonEsperado = buyerPayloadResponse.write(
                new BuyerPayloadResponse(buyer)
        ).getJson();


        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(jsonEsperado, response.getContentAsString());

    }
    
    

    }