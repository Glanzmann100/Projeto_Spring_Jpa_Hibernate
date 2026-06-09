package com.Glanzmann100.Projeto_de_Produtos.services;

import com.Glanzmann100.Projeto_de_Produtos.models.Product;
import com.Glanzmann100.Projeto_de_Produtos.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> product = repository.findById(id);
        return product.get();
    }
    public Product insert(Product product) {
        return repository.save(product);
    }
}
