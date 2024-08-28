package com.burguer_server.services;

import com.burguer_server.user.User;
import com.burguer_server.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User User) {
        User.setPassword(passwordEncoder.encode(User.getPassword())); //Transforma a senha que digitar em BCrypt
        return repository.save(User);
    }

    public List<User> findAll() {
        return repository.findAll();
    }
}
