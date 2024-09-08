package com.burguer_server.services;

import com.burguer_server.model.order.Order;
import com.burguer_server.model.order.OrderItem;
import com.burguer_server.model.product.Product;
import com.burguer_server.model.user.Buyer;
import com.burguer_server.payloads.buyer.BuyerPayloadRequest;

import com.burguer_server.payloads.order.OrderPayloadRequest;
import com.burguer_server.payloads.products.ProductsPayloadRequest;
import com.burguer_server.repositories.BuyerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuyerService {

    @Autowired
    private BuyerRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private AdressService adressService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProductService productService;

    public Buyer save(BuyerPayloadRequest payload) {
        var buyerConvertido = new Buyer(payload);
        buyerConvertido.setPassword(passwordEncoder.encode(buyerConvertido.getPassword())); //Transforma a senha que digitar em BCrypt
        adressService.save(payload.buyerAdress());

        if (payload.buyerOrdersHistory() != null) {
            payload.buyerOrdersHistory().stream().forEach(o -> orderService.save(o));
            payload.buyerOrdersHistory().stream().forEach(o -> o.setBuyer(buyerConvertido));

            buyerConvertido.setBuyerOrdersHistory(payload.buyerOrdersHistory());
        }

        return repository.save(buyerConvertido);
    }

    public Buyer findById(Long id) {
        var buyer = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("buyer não encontrado"));
        return buyer;
    }

    public List<Buyer> findAll() {
        var list = repository.findAll();
        return list;
    }

    public void deleteById(Long id) {
        var buyer = findById(id);
        adressService.deleteById(buyer.getBuyerAdress().getAdressId());

        repository.deleteById(id);
    }

    public Buyer update(Long id, BuyerPayloadRequest payload) {
        var buyer = findById(id);

        buyer.setEmail(payload.email());
        buyer.setPassword(passwordEncoder.encode(payload.password()));
        buyer.setBuyerFoneNumber(payload.buyerFoneNumber());
        buyer.setBuyerAdress(payload.buyerAdress());
        buyer.setBuyerOrdersHistory(payload.buyerOrdersHistory());

        adressService.update(buyer.getBuyerAdress().getAdressId(), payload.buyerAdress());
        return repository.save(buyer);
    }

    public Order createOrder(Long idBuyer, OrderPayloadRequest orderPayload) {
        // Busca o comprador pelo ID
        var buyer = findById(idBuyer);

        // Cria a nova ordem
        var orderCreated = new Order(orderPayload);
        orderCreated.setBuyer(buyer); // Associa o comprador à ordem

        // Cria e associa os itens do pedido

        if (2 > 1) {
            // Verifica se os produtos associados aos itens do pedido estão presentes
            var orderItems = orderPayload.orderItems();


                // Associa os itens à ordem e salva
                orderItems.forEach(oi -> oi.setOrder(orderCreated));
                orderItemService.saveAll(orderItems);
                orderCreated.setOrderItems(orderItems);

                // Cria e associa o pagamento à ordem
//                var payment = orderPayload.orderPayment();
//                payment.setOrder(orderCreated);
//                payment.setPaymentStatus(orderPayload.orderPayment().getPaymentStatus());
//                paymentService.save(payment); // Salva o pagamento

                // Calcula o total do pedido


                // Atualiza o histórico de pedidos do comprador
                if (buyer.getBuyerOrdersHistory() != null) {
                    buyer.getBuyerOrdersHistory().add(orderCreated);
                }

            } else {
                throw new IllegalArgumentException("Order must contain valid products.");
            }


        // Salva o comprador com o novo pedido no histórico
        repository.save(buyer);
        // Salva a ordem no banco de dados
        orderService.save(orderCreated);

        return orderCreated;
    }

    public Product saveProductInOrder(Long idBuyer,ProductsPayloadRequest payloadRequest) {
        var buyer = findById(idBuyer);
        var order = buyer.getBuyerOrdersHistory();

        List<OrderItem> orderItem = new ArrayList<>(List.of(
                new OrderItem(null, null, null, 1, "")
        ));

        orderItemService.saveAll(orderItem);

        var product = productService.save(payloadRequest);
        orderItem.stream().forEach(o -> o.setProduct(product));

        if (payloadRequest.orderItems() != null) {
            payloadRequest.orderItems().stream().forEach(p -> p.setProduct(new Product(payloadRequest)));
            orderItemService.saveAll(payloadRequest.orderItems());
            buyer.getBuyerOrdersHistory().stream().forEach(o -> o.getOrderItems().stream().forEach(p -> p.setProduct(new Product(payloadRequest))));
        }

        order.stream().forEach(o -> o.setOrderItems(orderItem));
        buyer.setBuyerOrdersHistory(order);
        product.setOrderItems(orderItem);
        productService.save(new ProductsPayloadRequest(product));
        repository.save(buyer);
        return product;
    }
}
