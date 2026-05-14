package com.Glanzmann100.Projeto_de_Produtos.controllers;

import com.Glanzmann100.Projeto_de_Produtos.models.order;
import com.Glanzmann100.Projeto_de_Produtos.services.orderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class orderController {

    @Autowired
    private orderService service;

    @GetMapping
    public ResponseEntity<List<order>> findAll() {
        List<order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public  ResponseEntity<order> findById(@PathVariable Long id) {
        order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);

    }
}
