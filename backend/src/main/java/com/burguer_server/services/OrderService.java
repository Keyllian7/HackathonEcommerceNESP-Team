package com.burguer_server.services;

import com.burguer_server.model.order.Order;
import com.burguer_server.model.order.OrderItem;
import com.burguer_server.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    
    @Autowired
    private OrderRepository repository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    public Order save(Order order){
        return repository.save(order);
    }

    public List<Order> saveAll(List<Order> order) {

        // Extrai uma lista de orderItems de todos os pedidos
        List<OrderItem> listOrderItem = order.stream().flatMap(o -> o.getOrderItems().stream()).collect(Collectors.toList());

//        // Extrai os produtos e garante que todos estejam salvos
//        var products = listOrderItem.stream().map(OrderItem::getProduct).collect(Collectors.toSet());
//        productService.saveAll(products); // Salva os produtos

        // Salva os orderItems depois que os produtos foram salvos
        orderItemService.saveAll(listOrderItem);

        // Salva os pagamentos associados aos pedidos
        var listPayment = order.stream().map(Order::getOrderPayment).collect(Collectors.toList());
        paymentService.saveAll(listPayment);

        // Salva o histórico de status dos pedidos
        var listOrderStatus = order.stream().map(o -> o.getStatusHistory()).collect(Collectors.toList());
        orderStatusService.saveAll(listOrderStatus);

        // Finalmente, salva os pedidos
        return repository.saveAll(order);
    }

    public Order findById(Long id) {
        var order = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order não encontrado"));
        return order;
    }

    public List<Order> findAll() {
        var list = repository.findAll();
        return list;
    }

    public void deleteById(Long id) {
        var order = findById(id);

        repository.deleteById(id);
    }

    public Order update(Long id, Order payload) {
        var order = findById(id);

        order.setOrderPayment(payload.getOrderPayment());
        order.setOrderItems(payload.getOrderItems());
        order.setBuyer(payload.getBuyer());
        order.setOrderTotal(payload.getOrderTotal());
        order.setOrderDeliveryTax(payload.getOrderDeliveryTax());
        order.setCurrentOrderStatus(payload.getCurrentOrderStatus());
        order.setStatusHistory(payload.getStatusHistory());

        return repository.save(order);
    }

}
