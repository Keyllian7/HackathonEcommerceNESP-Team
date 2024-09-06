package com.burguer_server.services;

import com.burguer_server.model.CartItem;
import com.burguer_server.repositories.CartItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

public class CartItemService {

    @Autowired
    private CartItemRepository repository;

    public CartItem save(CartItem cartItem){
        return repository.save(cartItem);
    }

    public CartItem findById(Long id) {
        var cartItem = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("CartItem não encontrado"));
        return cartItem;
    }

    public List<CartItem> findAll()  {

        return repository.findAll();
    }

    //Chama a classe product service para mostrar a lista de produtos
    public List<CartItem> findByCart(Long cartId) {
        var cart = findAll().stream().filter(p -> p.getCart().getCartId()==cartId).collect(Collectors.toList());

        return cart;
    }
}

