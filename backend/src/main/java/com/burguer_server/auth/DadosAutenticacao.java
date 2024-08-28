package com.burguer_server.auth;

import com.burguer_server.user.User;
import com.burguer_server.user.UserRole;

public record DadosAutenticacao(String email, String password, UserRole role) {
    public DadosAutenticacao(User u) {
        this(u.getEmail(), u.getPassword(), u.getRole());
    }
}
