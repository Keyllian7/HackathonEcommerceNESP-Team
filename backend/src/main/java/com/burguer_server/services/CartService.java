package com.burguer_server.services;

import com.burguer_server.model.Cart;
import com.burguer_server.model.CartItem;
import com.burguer_server.repositories.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CartService<cart> {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartRepository cartRepository;

    public Cart Save(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart findById(long id) {
        var cart = cartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cart não encontrado"));
        return cart;

    }

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }


    //Chama a classe product service para mostrar a lista de produtos
    public Set<CartItem> cartItems(Long idCart) {
        var cart = cartItemService.findByCart(idCart);

        return cart;
    }
}