package com.Glanzmann100.Projeto_de_Produtos.controllers;

import com.Glanzmann100.Projeto_de_Produtos.models.user;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class userController {

    @GetMapping
    public ResponseEntity<user> findAll() {
        user User = new user(1L, "Matheus", "matheus321@gmail.com", "9999999999", "01938436" );
        return ResponseEntity.ok().body(User);
    }
}
