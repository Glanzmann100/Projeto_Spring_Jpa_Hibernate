package com.Glanzmann100.Projeto_de_Produtos.repositories;

import com.Glanzmann100.Projeto_de_Produtos.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClientName(String name);
}
