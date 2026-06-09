package com.Glanzmann100.Projeto_de_Produtos.models;

import com.Glanzmann100.Projeto_de_Produtos.roles.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonPropertyOrder({"id", "name", "email", "password","phone","enabled","authorities"})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String password;

    public User(String email, String password, UserRoles role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // define as permissões do usuario
        if(this.role == UserRoles.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @JsonIgnore
    @Override
    public String getUsername() { // metodo que o spring usa para identificar o usuário, retorna email pois é o que eu defini para fazer o login
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() { // verifica se a conta expirou
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() { // verifica se a conta esta bloqueada
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() { // verifica se a conta expirou
        return true;
    }

    @Override
    public boolean isEnabled() { // verifica se a conta esta ativa
        return true;
    }
    @Override
    public String getPassword() {
        return password;
    }
}