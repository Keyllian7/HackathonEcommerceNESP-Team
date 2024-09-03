package com.burguer_server.services;

import com.burguer_server.model.user.Adress;
import com.burguer_server.repositories.AdressRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private AdressRepository repository;

    public Adress save(Adress adress) {

        return repository.save(adress);
    }

    public Adress findById(Long id) {
        var adress = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Adress não encontrado"));
        return adress;
    }

    public List<Adress> findAll() {
        var list = repository.findAll();
        return list;
    }

    public void deleteById(Long id) {
        var adress = findById(id);

        repository.deleteById(id);
    }

    public Adress update(Long id, Adress payload) {
        var adress = findById(id);

        adress.setAdressCep(payload.getAdressCep());
        adress.setAdressCity(payload.getAdressCity());
        adress.setAdressNumber(payload.getAdressNumber());
        adress.setAdressComplement(payload.getAdressComplement());
        adress.setAdressReference(payload.getAdressReference());

        return repository.save(adress);
    }

}
