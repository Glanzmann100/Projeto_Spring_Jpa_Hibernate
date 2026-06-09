package com.Glanzmann100.Projeto_de_Produtos.controllers;

import com.Glanzmann100.Projeto_de_Produtos.models.Product;
import com.Glanzmann100.Projeto_de_Produtos.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = service.findAll();
        return ResponseEntity.ok().body(products);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = service.findById(id);
        return ResponseEntity.ok().body(product);
    }
    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product product) {
        product = service.insert(product);
        return ResponseEntity.status(201).body(product);
    }
}
