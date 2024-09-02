package com.burguer_server.payloads.auth;

import com.burguer_server.model.user.User;
import com.burguer_server.model.enums.UserRole;

public record DadosAutenticacao(String email, String password, UserRole role) {
    public DadosAutenticacao(User u) {
        this(u.getEmail(), u.getPassword(), u.getRole());
    }
}
