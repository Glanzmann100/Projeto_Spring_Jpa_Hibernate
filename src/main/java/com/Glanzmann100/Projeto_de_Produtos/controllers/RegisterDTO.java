package com.Glanzmann100.Projeto_de_Produtos.controllers;

import com.Glanzmann100.Projeto_de_Produtos.roles.UserRoles;

public record RegisterDTO(String login, String password, UserRoles role) {
}
