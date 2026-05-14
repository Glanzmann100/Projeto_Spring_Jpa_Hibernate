package com.Glanzmann100.Projeto_de_Produtos.repositories;

import com.Glanzmann100.Projeto_de_Produtos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User, Long> {
}
