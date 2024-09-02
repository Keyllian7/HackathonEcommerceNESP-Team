package com.burguer_server.auth;

import com.burguer_server.model.User;
import com.burguer_server.model.enums.UserRole;

public record DadosAdmin(Long id, String email, String password, UserRole role) {
    public DadosAdmin(User user) {
        this(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
