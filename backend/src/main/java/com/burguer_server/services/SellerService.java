package com.burguer_server.services;

import com.burguer_server.payloads.auth.DadosAutenticacao;
import com.burguer_server.model.Seller;
import com.burguer_server.model.User;
import com.burguer_server.payloads.SellerPayloadRequest;
import com.burguer_server.repositories.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SellerService {

    @Autowired
    private SellerRepository repository;

    @Autowired
    private UserService userService;

    public Seller save(SellerPayloadRequest sellerPayload) {
        var sellerConvertido = new Seller(sellerPayload);
        var dadosAutenticacao = new DadosAutenticacao(sellerPayload.email(), sellerPayload.password(), sellerPayload.role());

        return repository.save(sellerConvertido);
    }


}
