package com.burguer_server.controllers;

import com.burguer_server.model.enums.ProductCategory;
import com.burguer_server.model.enums.UserRole;
import com.burguer_server.model.product.Product;
import com.burguer_server.model.product.Stock;
import com.burguer_server.model.user.Seller;
import com.burguer_server.payloads.products.ProductsPayloadResponse;
import com.burguer_server.payloads.seller.SellerPayloadRequest;
import com.burguer_server.payloads.seller.SellerPayloadResponse;
import com.burguer_server.repositories.ProductRepository;
import com.burguer_server.repositories.SellerRepository;
import com.burguer_server.repositories.StockRepository;
import com.burguer_server.services.ProductService;
import com.burguer_server.services.SellerService;
import com.burguer_server.services.StockService;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class SellerControllerTest {

    @Autowired
    private JacksonTester<SellerPayloadRequest> sellerPayloadRequest;

    @Autowired
    private JacksonTester<SellerPayloadResponse> sellerPayloadResponse;

    @Autowired
    private JacksonTester<List<ProductsPayloadResponse>> productsPayloadResponse;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SellerRepository sellerRepository;

    @MockBean
    private SellerService sellerService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductService productService;

    @MockBean
    private StockService stockService;

    @MockBean
    private StockRepository stockRepository;

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

    @Test
    @DisplayName("Procura o stock de um Seller")
    @WithMockUser(username = "user", roles = {"SELLER"})
    void findStockProducts() throws Exception{

        // Criação do produto
        var product = new Product(1L, ProductCategory.HAMBURGUER, null, "Hamburguer de siri", 25.00f,"", "bom", null );

        // Criação do conjunto de produtos
        Set<Product> products = new HashSet<>(Set.of(product));

        // Criação do stock com os produtos
        var stock = new Stock(1L, products);

        // Mock para salvar produtos e associar ao stock
        when(productService.saveAll(products)).thenReturn(products);
        products.forEach(p -> p.setStock(stock));

        // Mock para salvar o stock
        when(stockService.save(stock)).thenReturn(stock);

        // Criação do Seller com o stock
        var seller = new Seller(1L, "ricky@gmail.com", "123", UserRole.SELLER, stock);
        var sellerPayload = new SellerPayloadRequest(seller);

        // Mock para salvar o Seller
        when(sellerService.save(sellerPayload)).thenReturn(seller);

        // Salvar o Seller via API
        var saveResponse = mvc.perform(post("/seller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sellerPayloadRequest.write(new SellerPayloadRequest(seller)).getJson()))
                .andReturn().getResponse();

        // Verificar se o Seller foi salvo com sucesso
        Assertions.assertEquals(HttpStatus.CREATED.value(), saveResponse.getStatus());

        // Mock para buscar o stock do Seller
        when(sellerService.findStockProducts(1L)).thenReturn(products);

        List<ProductsPayloadResponse> productsPayloadResponseList = new ArrayList<>(List.of(new ProductsPayloadResponse(product)));

        // Gerar o JSON esperado de resposta com o produto no stock
        var jsonEsperado = productsPayloadResponse.write(productsPayloadResponseList).getJson();

        // Realizar a requisição GET para buscar o stock do Seller
        var response = mvc.perform(get("/seller/stock/1")).andReturn().getResponse();

        // Verificar se o status da resposta é OK (200)
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

        // Verificar se o JSON da resposta é o esperado
        Assertions.assertEquals(jsonEsperado, response.getContentAsString());
    }

    @Test
    @DisplayName("Atualiza Seller")
    @WithMockUser(username = "user", roles = {"SELLER"})
    void update() throws Exception{

        // Criação do Seller
        var seller = new Seller(1L, "ricky@gmail.com", "123", UserRole.SELLER, null);
        var sellerPayload = new SellerPayloadRequest(seller);

        // Mock para salvar o Seller
        when(sellerService.save(sellerPayload)).thenReturn(seller);

        // Salvar o Seller via API
        var saveResponse = mvc.perform(post("/seller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sellerPayloadRequest.write(new SellerPayloadRequest(seller)).getJson()))
                .andReturn().getResponse();

        // Verificar se o Seller foi salvo com sucesso
        Assertions.assertEquals(HttpStatus.CREATED.value(), saveResponse.getStatus());

        var sellerAtualizado = new Seller(1L, "ricky@hotmail.com", "123", UserRole.SELLER, null);

        var sellerPayloadAtualizado = new SellerPayloadRequest(sellerAtualizado);

        when(sellerService.update(1l, sellerPayloadAtualizado)).thenReturn(sellerAtualizado);

        var response = mvc.perform(put("/seller/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sellerPayloadRequest.write(
                        sellerPayloadAtualizado
                ).getJson())
        ).andReturn().getResponse();

        var jsonEsperado = sellerPayloadResponse.write(
                new SellerPayloadResponse(sellerAtualizado)
        ).getJson();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(jsonEsperado, response.getContentAsString());
    }

    @Test
    @DisplayName("Deleta Seller")
    @WithMockUser(username = "user", roles = {"SELLER"})
    void delete() throws Exception{

        // Criação do Seller
        var seller = new Seller(1L, "ricky@gmail.com", "123", UserRole.SELLER, null);
        var sellerPayload = new SellerPayloadRequest(seller);

        // Mock para salvar o Seller
        when(sellerService.save(sellerPayload)).thenReturn(seller);

        // Salvar o Seller via API
        var saveResponse = mvc.perform(post("/seller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sellerPayloadRequest.write(new SellerPayloadRequest(seller)).getJson()))
                .andReturn().getResponse();

        // Verificar se o Seller foi salvo com sucesso
        Assertions.assertEquals(HttpStatus.CREATED.value(), saveResponse.getStatus());

        doNothing().when(sellerService).deleteById(1l);

        var response = mvc.perform(MockMvcRequestBuilders.delete("/seller/" + 1l)
        ).andReturn().getResponse();

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());

    }


}