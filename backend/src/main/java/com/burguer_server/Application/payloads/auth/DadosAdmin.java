package com.burguer_server.Application.payloads.auth;

import com.burguer_server.Application.model.user.User;
import com.burguer_server.Application.model.user.UserRole;

public record DadosAdmin(Long id, String email, String password, UserRole role) {
    public DadosAdmin(User user) {
        this(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
