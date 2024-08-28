package com.burguer_server.auth;

import com.burguer_server.user.User;
import com.burguer_server.user.UserRole;

public record DadosAdmin(Long id, String email, String password, UserRole role) {
    public DadosAdmin(User user) {
        this(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
