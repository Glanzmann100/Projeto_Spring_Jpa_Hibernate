package com.Glanzmann100.Projeto_de_Produtos.services;

import com.Glanzmann100.Projeto_de_Produtos.models.Category;
import com.Glanzmann100.Projeto_de_Produtos.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }
    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }

};
