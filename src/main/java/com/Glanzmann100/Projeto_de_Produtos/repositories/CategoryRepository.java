package com.Glanzmann100.Projeto_de_Produtos.repositories;

import com.Glanzmann100.Projeto_de_Produtos.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
