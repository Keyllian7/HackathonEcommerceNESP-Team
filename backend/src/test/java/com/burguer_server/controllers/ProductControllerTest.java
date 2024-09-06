package com.burguer_server.controllers;

import com.burguer_server.model.enums.PaymentMethod;
import com.burguer_server.model.enums.PaymentStatus;
import com.burguer_server.model.enums.ProductCategory;
import com.burguer_server.model.enums.UserRole;
import com.burguer_server.model.order.Order;
import com.burguer_server.model.order.OrderItem;
import com.burguer_server.model.order.OrderStatus;
import com.burguer_server.model.payment.Payment;
import com.burguer_server.model.product.Product;
import com.burguer_server.model.user.Adress;
import com.burguer_server.model.user.Buyer;
import com.burguer_server.payloads.buyer.BuyerPayloadRequest;
import com.burguer_server.payloads.products.ProductsPayloadRequest;
import com.burguer_server.payloads.products.ProductsPayloadResponse;
import com.burguer_server.repositories.ProductRepository;
import com.burguer_server.services.*;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private JacksonTester<ProductsPayloadRequest> productsPayloadRequest;

    @Autowired
    private JacksonTester<ProductsPayloadResponse> productsPayloadResponse;

    @Autowired
    private JacksonTester<List<ProductsPayloadResponse>> productsPayloadResponseList;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private OrderItemService orderItemService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private BuyerService buyerService;

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private AdressService adressService;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("Salva produto")
    @WithMockUser(username = "user", roles = {"SELLER"})
    void save() throws Exception{

        var buyer = new Buyer(null, "valdir@gmail.com", "123", UserRole.SELLER, null, "85 99701420", null );
        var adress = new Adress(null, "57899000", "rua do java", "javacity", 12, "casa", "próximo ao javascript");

        buyer.setBuyerAdress(adress);

        var product = new Product(1l, ProductCategory.HAMBURGUER, null, "Hamburguer de siri", 25.00f, null, "bom demais", null);
        var payment = new Payment(null, null, 30.00f, false,PaymentMethod.CASH, PaymentStatus.PENDING);

        var orderStatus = new ArrayList<>(List.of(
           new OrderStatus(null, null, LocalDateTime.now(), null)
        ));

        //var order = new Order(null, buyer, null, 5.00, 30.00, payment, null,orderStatus);
        //payment.setOrder(order);
        //buyer.setBuyerOrdersHistory(new ArrayList<>(List.of(order)));
       /*var ordemItems = new ArrayList<>(List.of(
                new OrderItem(null,order ,product, 3, null )
        ));*/
        //product.setOrderItems(ordemItems);
        //order.setOrderItems(ordemItems);

        /*var orderList = new ArrayList<>(List.of(
           order
        ));*/
       //buyer.setBuyerOrdersHistory(orderList);

       var buyerPayload = new BuyerPayloadRequest(buyer);
        when(adressService.save(adress)).thenReturn(adress);
        when(buyerService.save(buyerPayload)).thenReturn(buyer);

        var productPayload = new ProductsPayloadRequest(product);
        //when(orderItemService.save(ordemItems.get(0))).thenReturn(ordemItems.get(0));
        when(productRepository.save(product)).thenReturn(product);
        when(productService.save(productPayload)).thenReturn(product);

        var response = mvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        productsPayloadRequest.write(
                                new ProductsPayloadRequest(product)
                        ).getJson()
                )).andReturn().getResponse();


        var jsonEsperado = productsPayloadResponse.write(
                new ProductsPayloadResponse(product)
        ).getJson();

        Assertions.assertEquals(201, response.getStatus());
        Assertions.assertEquals(jsonEsperado, response.getContentAsString());
    }

    @Test
    @DisplayName("Retorna lista de produtos")
    @WithMockUser(username = "user", roles = {"SELLER"})

    void findAll() throws Exception {
        var product = new Product(null, ProductCategory.HAMBURGUER, null, "Hamburguer de siri", 25.00f, null, "bom demais", null);

        var list = new ArrayList<>(List.of(
                product
        ));

        when(productRepository.saveAll(list)).thenReturn(list);
        when(productService.findAll()).thenReturn(list.stream().collect(Collectors.toSet()));
        var response = mvc.perform(get("/product/all")
                ).andReturn().getResponse();


        var jsonEsperado = productsPayloadResponseList.write(
                new ArrayList<>(List.of(new ProductsPayloadResponse(product)))
        ).getJson();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(jsonEsperado, response.getContentAsString());

    }
}