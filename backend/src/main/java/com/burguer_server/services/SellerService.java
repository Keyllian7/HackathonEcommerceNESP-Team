package com.burguer_server.services;

import com.burguer_server.model.product.Product;
import com.burguer_server.model.product.Stock;
import com.burguer_server.model.user.Seller;
import com.burguer_server.payloads.products.ProductsPayloadRequest;
import com.burguer_server.payloads.seller.SellerPayloadRequest;
import com.burguer_server.repositories.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SellerService {

    @Autowired
    private SellerRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    public Seller save(SellerPayloadRequest payload) {
        var sellerConvertido = new Seller(payload);
        sellerConvertido.setPassword(passwordEncoder.encode(sellerConvertido.getPassword())); // Transforma a senha em BCrypt

        if (payload.sellerStock() == null) {
            sellerConvertido.setSellerStock(null);
        }

        // Salva o seller
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

    public Stock saveProductInStockOfSeller(Long idSeller, ProductsPayloadRequest productsPayloadRequest) {
        // Busca o vendedor pelo ID
        var seller = findById(idSeller);

        // Obtém o estoque existente ou cria um novo se não existir
        var stock = seller.getSellerStock() != null ? seller.getSellerStock() : new Stock();

        // Converte os payloads de produto para entidades de produto
        var product = new Product(productsPayloadRequest);

        // Adiciona o produto ao estoque
        stock.getStockProduct().add(product);

        // Atualiza a referência do estoque no produto
        product.setStock(stock);

        // Salva o estoque, o que irá salvar o produto automaticamente devido ao cascade
        stockService.save(stock);

        // Atualiza o estoque no vendedor, se necessário
        seller.setSellerStock(stock);
        repository.save(seller);

        return stock;
    }

    public Stock findStockBySeller(Long idSeller) {
        var seller = findById(idSeller);
        var stockId = seller.getSellerStock().getStockId();

        return stockService.findById(stockId);
    }

    //Mostra a lista de produtos que existem no stock do seller
    public Set<Product> findStockProducts(Long idSeller) {
        var seller = findById(idSeller);
        var idStock = findById(idSeller).getSellerStock().getStockId();
        var list = stockService.stockProducts(idStock);
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
