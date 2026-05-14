package com.Glanzmann100.Projeto_de_Produtos.config;

import com.Glanzmann100.Projeto_de_Produtos.models.order;
import com.Glanzmann100.Projeto_de_Produtos.models.user;
import com.Glanzmann100.Projeto_de_Produtos.repositories.orderRepository;
import com.Glanzmann100.Projeto_de_Produtos.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class testConfig implements CommandLineRunner {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private orderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {

        user u1 = new user(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        user u2 = new user(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        order o1 = new order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
        order o2 = new order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
        order o3 = new order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}
