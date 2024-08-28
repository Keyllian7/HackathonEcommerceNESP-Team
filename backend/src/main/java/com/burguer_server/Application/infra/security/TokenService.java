package com.burguer_server.Application.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.burguer_server.Application.model.user.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String geraToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("12345");
            return JWT.create()
                    .withIssuer("Application") //Nome da api
                    .withSubject(user.getEmail()) //o email da pessoa pra quem tá gerando
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);

        } catch (JWTCreationException exception){
            throw new RuntimeException("Deu erro ao gerar token! ", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("12345");
            return JWT.require(algorithm)
                    .withIssuer("Application")
                    .build()
                    .verify(tokenJWT) //Verifica o token se existe e se é válido
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Deu erro no token service ao pegar subject " + exception);
        }

    }
}
