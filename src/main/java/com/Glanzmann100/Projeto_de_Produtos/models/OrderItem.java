package com.Glanzmann100.Projeto_de_Produtos.models;

import com.Glanzmann100.Projeto_de_Produtos.models.modelsPK.OrderItemPk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@JsonPropertyOrder({"quantity", "price", "subTotal", "product"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @EmbeddedId
    private OrderItemPk id = new OrderItemPk(); // Referencia a chave composta (OrderItemPK)

    private Integer quantity;

    private Double price;

    @JsonIgnore
    public OrderItemPk getId() {
        return id;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder(); // chave composta id Order
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct(); // chave composta id Product
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}