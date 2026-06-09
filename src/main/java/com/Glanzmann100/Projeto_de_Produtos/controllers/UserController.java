package com.Glanzmann100.Projeto_de_Produtos.controllers;

import com.Glanzmann100.Projeto_de_Produtos.models.User;
import com.Glanzmann100.Projeto_de_Produtos.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder       //Monta o link de onde o novo usuário pode ser encontrado depois de criado
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) { //Cria um novo obj usando o molde da classe User e captura o id
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<User> findByName(@PathVariable String name) {
        User user = service.findByName(name);
        return ResponseEntity.ok().body(user);
    }
    @GetMapping(value = "/count")
    public ResponseEntity<Integer> userCount() {
        int count = service.userCount();
        return ResponseEntity.ok().body(count);
    }
}
