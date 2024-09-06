package com.burguer_server.services;

import com.burguer_server.model.user.Buyer;
import com.burguer_server.payloads.buyer.BuyerPayloadRequest;

import com.burguer_server.repositories.BuyerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Buyer save(BuyerPayloadRequest payload) {
        var buyerConvertido = new Buyer(payload);
        buyerConvertido.setPassword(passwordEncoder.encode(buyerConvertido.getPassword())); //Transforma a senha que digitar em BCrypt
        adressService.save(payload.buyerAdress());

        payload.buyerOrdersHistory().stream().forEach(o -> orderService.save(o));
        payload.buyerOrdersHistory().stream().forEach(o -> o.setBuyer(buyerConvertido));

         buyerConvertido.setBuyerOrdersHistory(payload.buyerOrdersHistory());

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
}
