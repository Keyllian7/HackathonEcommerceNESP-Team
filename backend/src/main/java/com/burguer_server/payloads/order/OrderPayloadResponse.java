package com.burguer_server.payloads.order;

import com.burguer_server.model.order.Order;
import com.burguer_server.model.order.OrderItem;
import com.burguer_server.model.order.OrderStatus;
import com.burguer_server.model.payment.Payment;
import com.burguer_server.model.product.Product;
import com.burguer_server.model.product.Stock;
import com.burguer_server.model.user.Buyer;

import java.util.List;
import java.util.Set;

public record OrderPayloadResponse (Long id,
                                    Buyer buyer,
                                    List<OrderItem> orderItems,
                                    double orderDeliveryTax,
                                    double orderTotal,
                                    Payment orderPayment,
                                    String currentOrderStatus,
                                    List<OrderStatus> statusHistory){
    public OrderPayloadResponse(Order order) {
        this(order.getOrderId(),
                order.getBuyer(),
                order.getOrderItems(),
                order.getOrderDeliveryTax(),
                order.getOrderTotal(),
                order.getOrderPayment(),
                order.getCurrentOrderStatus(),
                order.getStatusHistory());
    }
}
