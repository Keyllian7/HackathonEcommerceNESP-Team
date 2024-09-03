package com.burguer_server.services;

import com.burguer_server.model.product.Product;
import com.burguer_server.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    public Product save(Product Product) {

        return repository.save(Product);
    }

    public Set<Product> saveAll(Set<Product> products) {
        repository.saveAll(products);
        return products;
    }

    //Pelo id do stock mostra os produtos que estão nele
    public Set<Product> findByStock(Long idStock) {
        var list = findAll().stream().filter(p -> p.getStock().getStockId() == idStock).collect(Collectors.toSet());
        return list;
    }


    public Product findById(Long id) {
        var Product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product não encontrado"));
        return Product;
    }

    public List<Product> findAll() {
        var list = repository.findAll();
        return list;
    }

    public void deleteById(Long id) {
        var Product = findById(id);

        repository.deleteById(id);
    }

    public Product update(Long id, Product payload) {
        var product = findById(id);

        product.setProductCategory(payload.getProductCategory());
        product.setOrderItems(payload.getOrderItems());
        product.setProductName(payload.getProductName());
        product.setProductPrice(payload.getProductPrice());
        product.setProductImageLink(payload.getProductImageLink());
        product.setStock(payload.getStock());

        return repository.save(product);
    }

}
