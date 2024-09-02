package com.burguer_server.services;

import com.burguer_server.model.user.Seller;
import com.burguer_server.payloads.seller.SellerPayloadRequest;
import com.burguer_server.repositories.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SellerService {

    @Autowired
    private SellerRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Seller save(SellerPayloadRequest payload) {
        var sellerConvertido = new Seller(payload);
        sellerConvertido.setPassword(passwordEncoder.encode(sellerConvertido.getPassword())); //Transforma a senha que digitar em BCrypt

        return repository.save(sellerConvertido);
    }

    public Seller findById(Long id) {
        var seller = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Seller não encontrado"));
        return seller;
    }

    public List<Seller> findAll() {
        var list = repository.findAll();
        return list;
    }

    public void deleteById(Long id) {
        var seller = findById(id);

        repository.deleteById(id);
    }

    public Seller update(Long id, SellerPayloadRequest payload) {
        var seller = findById(id);

        seller.setEmail(payload.email());
        seller.setPassword(passwordEncoder.encode(payload.password()));
        seller.setSellerStock(payload.sellerStock());

        return repository.save(seller);
    }


}
