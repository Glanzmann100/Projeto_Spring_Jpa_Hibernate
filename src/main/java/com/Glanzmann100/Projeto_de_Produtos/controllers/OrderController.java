package com.Glanzmann100.Projeto_de_Produtos.controllers;

import com.Glanzmann100.Projeto_de_Produtos.models.Order;
import com.Glanzmann100.Projeto_de_Produtos.services.OrderService;
import com.Glanzmann100.Projeto_de_Produtos.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public  ResponseEntity<Order> findById(@PathVariable Long id) {
        Order order = service.findById(id);
        return ResponseEntity.ok().body(order);

    }
    @GetMapping("/client/{name}")
    public ResponseEntity<List<Order>> findByClientName(@PathVariable String name) {
        List<Order> orders = service.findByClientName(name);
        return ResponseEntity.ok().body(orders);
    }
    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order order) {
        order = service.insert(order);
        return ResponseEntity.status(201).body(order);
    }
}
