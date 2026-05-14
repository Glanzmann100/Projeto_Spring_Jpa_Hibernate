package com.Glanzmann100.Projeto_de_Produtos.repositories;

import com.Glanzmann100.Projeto_de_Produtos.models.order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderRepository extends JpaRepository<order, Long> {
}
