package com.Glanzmann100.Projeto_de_Produtos.infra.security;

import com.Glanzmann100.Projeto_de_Produtos.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) { // recebe o User cria um token assinado com a secret define o emissor como o nome do projeto coloca o email dentro do token e define a expiração
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); // Recebe por parametro uma secret (hash unico)
            String token = JWT.create()
                    .withIssuer("projeto_produtos")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) { // receb o token e verifica se ele e valido e retorna o email que esta dentro dese se for invlaido retorna vazio
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("projeto_produtos")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant genExpirationDate() { // gera data de expiração de 2 horas
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
