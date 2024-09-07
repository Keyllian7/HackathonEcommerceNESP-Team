package com.burguer_server.payloads.products;

import com.burguer_server.model.enums.ProductCategory;
import com.burguer_server.model.order.OrderItem;
import com.burguer_server.model.product.Product;
import com.burguer_server.model.product.Stock;

import java.util.List;

public record ProductsPayloadRequest(ProductCategory productCategory,
                                     List<OrderItem> orderItems,
                                     String productName,
                                     float productPrice,
                                     String productImageLink,
                                     String productDescription,
                                     Stock stock) {

    public ProductsPayloadRequest(Product product) {
        this( product.getProductCategory(), product.getOrderItems(), product.getProductName(), product.getProductPrice(), product.getProductImageLink(), product.getProductDescription(), product.getStock());
    }
}
