package com.Glanzmann100.Projeto_de_Produtos.services;

import com.Glanzmann100.Projeto_de_Produtos.models.Order;
import com.Glanzmann100.Projeto_de_Produtos.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class orderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }
    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.get();
    }
}
