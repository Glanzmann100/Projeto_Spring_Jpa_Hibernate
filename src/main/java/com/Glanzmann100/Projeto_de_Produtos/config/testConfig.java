package com.Glanzmann100.Projeto_de_Produtos.config;

import com.Glanzmann100.Projeto_de_Produtos.enums.OrderStatus;
import com.Glanzmann100.Projeto_de_Produtos.models.*;
import com.Glanzmann100.Projeto_de_Produtos.models.modelsPK.OrderItemPk;
import com.Glanzmann100.Projeto_de_Produtos.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
@Profile("test")
public class testConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456", new ArrayList<>());
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456", new ArrayList<>());
        userRepository.saveAll(Arrays.asList(u1, u2));

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID.getCode(), u1, new HashSet<>(), null);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT.getCode(), u2, new HashSet<>(), null);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.CANCELED.getCode(), u1, new HashSet<>(), null);
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        Category cat1 = new Category(null, "Electronics", new HashSet<>());
        Category cat2 = new Category(null, "Books", new HashSet<>());
        Category cat3 = new Category(null, "Computers", new HashSet<>());
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "", new HashSet<>(), new HashSet<>());
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "", new HashSet<>(), new HashSet<>());
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "", new HashSet<>(), new HashSet<>());
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "", new HashSet<>(), new HashSet<>());
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "", new HashSet<>(), new HashSet<>());

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        OrderItem oi1 = new OrderItem(new OrderItemPk(), 2, p1.getPrice());
        oi1.setOrder(o1);
        oi1.setProduct(p1);

        OrderItem oi2 = new OrderItem(new OrderItemPk(), 1, p3.getPrice());
        oi2.setOrder(o1);
        oi2.setProduct(p3);

        OrderItem oi3 = new OrderItem(new OrderItemPk(), 2, p3.getPrice());
        oi3.setOrder(o2);
        oi3.setProduct(p3);

        OrderItem oi4 = new OrderItem(new OrderItemPk(), 2, p5.getPrice());
        oi4.setOrder(o3);
        oi4.setProduct(p5);

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
        o1.setPayment(pay1);

        orderRepository.save(o1);
    }
}
