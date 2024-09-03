package com.burguer_server.services;

import com.burguer_server.model.product.Product;
import com.burguer_server.model.product.Stock;
import com.burguer_server.repositories.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StockService {

    @Autowired
    private ProductService productService;

    @Autowired
    private StockRepository repository;

    public Stock save(Stock stock){
        var productsSaved = productService.saveAll(stock.getStockProduct());

        productService.saveAll(productsSaved);

        return repository.save(stock);
    }

    public Stock findById(Long id) {
        var stock = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Stock não encontrado"));
        return stock;
    }

    public List<Stock> findAll() {
        return repository.findAll();
    }

    //Chama a classe product service para mostrar a lista de produtos
    public Set<Product> stockProducts(Long idStock) {
        var stock = productService.findByStock(idStock);

        return stock;
    }
}
