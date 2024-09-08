package com.burguer_server.payloads.orderitem;

import com.burguer_server.model.order.OrderItem;
import com.burguer_server.model.product.Product;

public record OrderItemPayloadResponse (
        Long orderItemId, // ID do item de pedido
        Product product,  // Produto associado
        int quantity,     // Quantidade do produto no pedido
        String notes      // Notas adicionais sobre o item do pedido
) {
    public OrderItemPayloadResponse(OrderItem orderItem) {
        this(orderItem.getOrderItemId(), orderItem.getProduct(), orderItem.getQuantity(), orderItem.getNotes());
    }
}