package com.Glanzmann100.Projeto_de_Produtos.models;

import com.Glanzmann100.Projeto_de_Produtos.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatusCode;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "id.order") // Relacionamento que referencia o id order da classe OrderItem
    private Set<OrderItem> items = new HashSet<>(); // cria a lista de items atrelados ao Order

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // tanto o Order quanto o Payment devem te ro mesmo ID
    private Payment payment;

    public Order() {}

    public Order(Long id, Instant moment, OrderStatus orderStatus , User client) {
        super();
        this.id = id;
        this.orderStatusCode = (orderStatus != null) ? orderStatus.getCode() : null;
        this.moment = moment;
        this.client = client;
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(this.orderStatusCode);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null)
            this.orderStatusCode = orderStatus.getCode();
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Payment getPayment() {return payment;}

    public void setPayment(Payment payment) {this.payment = payment;}

    public Set<OrderItem> getItems() {
        return items;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(moment, order.moment) && Objects.equals(client, order.client);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
