package com.burguer_server.services;

import com.burguer_server.model.order.Order;
import com.burguer_server.model.user.Buyer;
import com.burguer_server.payloads.buyer.BuyerPayloadRequest;

import com.burguer_server.payloads.order.OrderPayloadRequest;
import com.burguer_server.repositories.BuyerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Order> createOrder(OrderPayloadRequest orderPayload) {
        // Cria uma nova ordem a partir do payload
        var order = new Order(orderPayload);

        // Obtém os itens do pedido (OrderItems)
        var orderItems = order.getOrderItems();

        // Salva os itens do pedido
        orderItemService.saveAll(orderItems);

        // Calcula o total dos produtos multiplicando a quantidade pelo preço
        double total = orderItems.stream()
                .mapToDouble(item -> item.getProduct().getProductPrice() * item.getQuantity())
                .sum();

        // Define o total calculado no pedido
        order.setOrderTotal(total);

        // Salva a ordem e retorna a resposta (essa parte você pode ajustar conforme sua lógica)
        orderService.save(order);

        return List.of(order);
    }

}
