package com.burguer_server.services;

import com.burguer_server.model.order.OrderStatus;
import com.burguer_server.repositories.OrderStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderStatusService {
    
    @Autowired
    private OrderStatusRepository repository;

    public OrderStatus save(OrderStatus orderStatus) {

        return repository.save(orderStatus);
    }

    public List<OrderStatus> saveAll(List<List<OrderStatus>> orderStatus) {
        // Transforma em uma única lista
        List<OrderStatus> flattenedList = orderStatus.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        // Atualiza o timestamp dos OrderStatus
        flattenedList.forEach(o -> o.setOrderStatusTimeStamp(LocalDateTime.now()));

        // Salva todas as OrderStatus
        return repository.saveAll(flattenedList);
    }


    public OrderStatus findById(Long id) {
        var orderStatus = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("OrderStatus não encontrado"));
        return orderStatus;
    }

    public List<OrderStatus> findAll() {
        var list = repository.findAll();
        return list;
    }

    public void deleteById(Long id) {
        var OrderStatus = findById(id);

        repository.deleteById(id);
    }

    public OrderStatus update(Long id, OrderStatus payload) {
        var orderStatus = findById(id);

        orderStatus.setOrder(payload.getOrder());
        orderStatus.setOrderStatusDescription(payload.getOrderStatusDescription());
        orderStatus.setOrderStatusTimeStamp(payload.getOrderStatusTimeStamp());

        return repository.save(orderStatus);
    }

}
