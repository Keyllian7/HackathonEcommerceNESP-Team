package com.burguer_server.services;

import com.burguer_server.model.enums.ProductCategory;
import com.burguer_server.model.product.Product;
import com.burguer_server.payloads.products.ProductsPayloadRequest;
import com.burguer_server.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    @Autowired
    private OrderItemService orderItemService;
    
    @Autowired
    private ProductRepository repository;

    public Product save(ProductsPayloadRequest payloadRequest) {

        var productConvertido = new Product(payloadRequest);

        if (payloadRequest.orderItems() != null) {
            var orderItems = payloadRequest.orderItems();
            orderItemService.saveAll(orderItems);

            repository.save(productConvertido);

            orderItems.stream().forEach(p -> p.setProduct(productConvertido));
            orderItemService.saveAll(orderItems);

            productConvertido.setOrderItems(orderItems);
        }

        return repository.save(productConvertido);
    }

    public Set<Product> saveAll(Set<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Product list must not be empty or null.");
        }

        return new HashSet<>(repository.saveAll(products)); // Retorna os produtos salvos
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

    public Set<Product> findAll() {
        var list = repository.findAll();
        var setList = list.stream().collect(Collectors.toSet());
        return setList;
    }

    public Set<Product> findHamburgueres() {
        var list = findAll().stream().filter(p -> p.getProductCategory() == ProductCategory.HAMBURGUER).collect(Collectors.toSet());
        return list;
    }

    public Set<Product> findDrinks() {
        var list = findAll().stream().filter(p -> p.getProductCategory() == ProductCategory.DRINK).collect(Collectors.toSet());
        return list;
    }

    public void deleteById(Long id) {
        var product = findById(id);

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
