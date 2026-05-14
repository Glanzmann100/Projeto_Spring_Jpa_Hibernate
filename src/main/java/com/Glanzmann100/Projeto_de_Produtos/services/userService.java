package com.Glanzmann100.Projeto_de_Produtos.services;

import com.Glanzmann100.Projeto_de_Produtos.models.User;
import com.Glanzmann100.Projeto_de_Produtos.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class userService {

    @Autowired
    private userRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }
    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.get();
    }
}
