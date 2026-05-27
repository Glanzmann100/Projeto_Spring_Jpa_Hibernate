package com.Glanzmann100.Projeto_de_Produtos.models;

import com.Glanzmann100.Projeto_de_Produtos.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@JsonPropertyOrder({"id", "moment", "orderStatus", "total", "payment", "client", "items"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @Getter(AccessLevel.NONE)
    private Integer orderStatusCode;

    @JsonIgnore
    public Integer getOrderStatusCode() {
        return orderStatusCode;
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "id.order") // Relacionamento que referencia o id order da classe OrderItem
    private Set<OrderItem> items = new HashSet<>(); // cria a lista de items atrelados ao Order

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // tanto o Order quanto o Payment devem te ro mesmo ID
    private Payment payment;

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(this.orderStatusCode);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null)
            this.orderStatusCode = orderStatus.getCode();
    }

    @JsonIgnore
    public Payment getPayment() {
        return payment;
    }

    public Double getTotal() { // Comando para o valor total de todos os itens de cada pedido
        double sum = 0.0;
        for (OrderItem x : items) {
            sum += x.getSubTotal();
        }
        return sum;
    }
}