package com.Glanzmann100.Projeto_de_Produtos.services;

import com.Glanzmann100.Projeto_de_Produtos.models.User;
import com.Glanzmann100.Projeto_de_Produtos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // Chama o metodo loadUserByUsername(String email) e procura o usuario no banco se nao achar retorna um erro
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return org.springframework.security.core.userdetails.User  // Aqui eu converto o usuario do banco para um formato que o Spring entende
                .withUsername(user.getEmail()) // login como email
                .password(user.getPassword()) // senha
                .roles("USER") // papel do usuário
                .build(); // cria um novo objeto
    }
}