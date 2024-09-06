package com.burguer_server.controllers;

import com.burguer_server.model.enums.UserRole;
import com.burguer_server.model.user.Seller;
import com.burguer_server.payloads.products.ProductsPayloadRequest;
import com.burguer_server.payloads.products.ProductsPayloadResponse;
import com.burguer_server.payloads.seller.SellerPayloadRequest;
import com.burguer_server.payloads.seller.SellerPayloadResponse;
import com.burguer_server.repositories.ProductRepository;
import com.burguer_server.repositories.SellerRepository;
import com.burguer_server.services.OrderItemService;
import com.burguer_server.services.SellerService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class SellerControllerTest {

    @Autowired
    private JacksonTester<SellerPayloadRequest> sellerPayloadRequest;

    @Autowired
    private JacksonTester<SellerPayloadResponse> sellerPayloadResponse;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SellerRepository sellerRepository;

    @MockBean
    private SellerService sellerService;

    @Test
    @DisplayName("Salva Seller")
    @WithMockUser(username = "user", roles = {"SELLER"})
    void save() throws Exception{

        var seller = new Seller(1l, "ricky@gmail.com", "123", UserRole.SELLER, null);

        var sellerPayload = new SellerPayloadRequest(seller);

        when(sellerRepository.save(seller)).thenReturn(seller);
        when(sellerService.save(sellerPayload)).thenReturn(seller);

        var response = mvc.perform(post("/seller")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        sellerPayloadRequest.write(
                                new SellerPayloadRequest(seller)
                        ).getJson()
                )).andReturn().getResponse();

        var jsonEsperado = sellerPayloadResponse.write(
                new SellerPayloadResponse(seller)
        ).getJson();

        Assertions.assertEquals(201, response.getStatus());
        Assertions.assertEquals(jsonEsperado, response.getContentAsString());


    }

}