package com.burguer_server.payloads.stock;

import com.burguer_server.model.product.Product;
import com.burguer_server.model.product.Stock;

import java.util.Set;

public record StockPayloadRequest(Set<Product> stockProduct) {

    public StockPayloadRequest(Stock stock) {
        this(stock.getStockProduct());
    }
}
