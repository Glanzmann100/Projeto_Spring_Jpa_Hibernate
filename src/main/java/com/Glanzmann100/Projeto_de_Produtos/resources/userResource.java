package com.Glanzmann100.Projeto_de_Produtos.resources;

import com.Glanzmann100.Projeto_de_Produtos.entities.user;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class userResource {

    @GetMapping
    public ResponseEntity<user> findAll() {
        user u = new user(1L, "Matheus", "matheus321@gmail.com", "9999999999", "01938436" );
        return ResponseEntity.ok().body(u);
    }
}
