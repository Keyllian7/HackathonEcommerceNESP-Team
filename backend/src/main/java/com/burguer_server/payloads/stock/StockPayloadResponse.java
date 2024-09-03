package com.burguer_server.payloads.stock;

import com.burguer_server.model.product.Product;
import com.burguer_server.model.product.Stock;

import java.util.Set;

public record StockPayloadResponse(Long id,
                                   Set<Product> stockProduct) {

    public StockPayloadResponse(Stock stock) {
        this(stock.getStockId(), stock.getStockProduct());
    }
}
