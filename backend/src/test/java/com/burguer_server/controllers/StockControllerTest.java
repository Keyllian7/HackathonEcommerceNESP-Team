package com.burguer_server.controllers;

import com.burguer_server.model.enums.ProductCategory;
import com.burguer_server.model.enums.UserRole;
import com.burguer_server.model.product.Product;
import com.burguer_server.model.product.Stock;
import com.burguer_server.model.user.Seller;
import com.burguer_server.payloads.products.ProductsPayloadRequest;
import com.burguer_server.payloads.products.ProductsPayloadResponse;
import com.burguer_server.payloads.seller.SellerPayloadRequest;
import com.burguer_server.payloads.stock.StockPayloadRequest;
import com.burguer_server.payloads.stock.StockPayloadResponse;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class StockControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SellerRepository sellerRepository;

    @Autowired
    private JacksonTester<SellerPayloadRequest> sellerPayloadRequest;

    @Autowired
    private JacksonTester<StockPayloadRequest> stockPayloadRequest;

    @Autowired
    private JacksonTester<ProductsPayloadRequest> productsPayloadRequest;
    @MockBean
    private SellerService sellerService;

    @Autowired
    private JacksonTester<List<ProductsPayloadRequest>> productPayloadRequest;

    @Autowired
    private JacksonTester<List<ProductsPayloadResponse>> productsPayloadResponse;

    @Autowired
    private JacksonTester<StockPayloadResponse> stockPayloadResponse;

    @MockBean
    private ProductService productService;

    @MockBean
    private StockService stockService;

    @MockBean
    private StockRepository stockRepository;

    @Test
    @DisplayName("Salva Stock em seller")
    @WithMockUser(username = "user", roles = {"SELLER"})
    void save() throws Exception {

        // Criação do Seller
        var seller = new Seller(1L, "ricky@gmail.com", "123", UserRole.SELLER, null);
        var sellerPayload = new SellerPayloadRequest(seller);

        // Mock para salvar o Seller
        when(sellerService.save(sellerPayload)).thenReturn(seller);

        // Simular requisição para salvar o Seller
        var saveResponse = mvc.perform(post("/seller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sellerPayloadRequest.write(new SellerPayloadRequest(seller)).getJson()))
                .andReturn().getResponse();

        // Verificar se o Seller foi salvo com sucesso
        Assertions.assertEquals(HttpStatus.CREATED.value(), saveResponse.getStatus());

        // Criação do produto
        var product = new Product(1L, ProductCategory.HAMBURGUER, null, "Hamburguer de siri", 25.00f, "", "bom", null);

        var productPayload = new ProductsPayloadRequest(product);
        // Criação do conjunto de produtos
        Set<Product> products = new HashSet<>(Set.of(product));

        // Mock para salvar produtos e associar ao estoque
        when(productService.saveAll(products)).thenReturn(products);

        // Criação do Stock com os produtos
        var stock = new Stock(1L, products);


        // Mock para o Seller e as operações de estoque
        when(sellerService.findById(1L)).thenReturn(seller);
        when(sellerRepository.save(seller)).thenReturn(seller);

        // Mock para salvar o produto individualmente
        var productPayloadRequest = new ProductsPayloadRequest(product);
        when(productService.save(productPayloadRequest)).thenReturn(product);

        // Mock para salvar o estoque no Seller
        when(sellerService.saveProductInStockOfSeller(1L, productPayloadRequest)).thenReturn(stock);

        // Simular requisição para salvar Stock no Seller
        var response = mvc.perform(post("/stock/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productsPayloadRequest.write(productPayload).getJson()))
                .andReturn().getResponse();

        // Gerar o JSON esperado de resposta com o produto no Stock
        var jsonEsperado = stockPayloadResponse.write(new StockPayloadResponse(stock)).getJson();

        // Verificar se o Stock foi salvo com sucesso
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        // Verificar se o JSON da resposta é o esperado
        Assertions.assertEquals(jsonEsperado, response.getContentAsString());
    }

    @Test
    @DisplayName("Retorna uma lista de Stock")
    @WithMockUser(username = "user", roles = {"SELLER"})
    void findStockBySeller() throws Exception {

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

        var stockPayload = new StockPayloadRequest(stock);
        var productsPayloadRequest = new ProductsPayloadRequest(product);

        when(sellerService.findById(1l)).thenReturn(seller);
        when(productService.saveAll(products)).thenReturn(products);
        when(stockService.save(stock)).thenReturn(stock);
        when(sellerService.saveProductInStockOfSeller(1l, productsPayloadRequest)).thenReturn(stock);
        when(sellerRepository.save(seller)).thenReturn(seller);

        when(sellerService.findStockBySeller(1l)).thenReturn(stock);

        var response = mvc.perform(get("/stock/stock/1")).andReturn().getResponse();
        var jsonEsperado = stockPayloadResponse.write(new StockPayloadResponse(stock)).getJson();

        // Verificar status
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

        // Verificar se o JSON da resposta é o esperado
        Assertions.assertEquals(jsonEsperado, response.getContentAsString());

    }
}