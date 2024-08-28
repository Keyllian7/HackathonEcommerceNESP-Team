package com.burguer_server.Application.payloads.auth;

import com.burguer_server.Application.model.user.User;
import com.burguer_server.Application.model.user.UserRole;

public record DadosAutenticacao(String email, String password, UserRole role) {
    public DadosAutenticacao(User u) {
        this(u.getEmail(), u.getPassword(), u.getRole());
    }
}
