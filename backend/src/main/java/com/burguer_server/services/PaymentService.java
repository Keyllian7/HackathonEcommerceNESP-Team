package com.burguer_server.services;

import com.burguer_server.model.payment.Payment;
import com.burguer_server.repositories.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PaymentService {
    
    @Autowired
    private PaymentRepository repository;

    public Payment save(Payment payment) {

        return repository.save(payment);
    }

    public List<Payment> saveAll(List<Payment> payment) {

        return repository.saveAll(payment);
    }

    public Payment findById(Long id) {
        var payment = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("payment não encontrado"));
        return payment;
    }

    public List<Payment> findAll() {
        var list = repository.findAll();
        return list;
    }

    public void deleteById(Long id) {
        var payment = findById(id);

        repository.deleteById(id);
    }

    public Payment update(Long id, Payment payload) {
        var payment = findById(id);

        payment.setPaymentMethod(payload.getPaymentMethod());
        payment.setPaymentStatus(payload.getPaymentStatus());
        payment.setPaid(payload.isPaid());
        payment.setTotal(payload.getTotal());
        payment.setOrder(payload.getOrder());

        return repository.save(payment);
    }

}
