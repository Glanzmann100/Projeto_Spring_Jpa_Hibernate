package com.Glanzmann100.Projeto_de_Produtos.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_order")
public class order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private user client;

    public order() {
    }

    public order(Long id, Instant moment, user client) {
        super();
        this.id = id;
        this.moment = moment;
        this.client = client;
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

    public user getClient() {
        return client;
    }

    public void setClient(user client) {
        this.client = client;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        order order = (order) o;
        return Objects.equals(id, order.id) && Objects.equals(moment, order.moment) && Objects.equals(client, order.client);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
