package com.Glanzmann100.Projeto_de_Produtos.repositories;

import com.Glanzmann100.Projeto_de_Produtos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
}
