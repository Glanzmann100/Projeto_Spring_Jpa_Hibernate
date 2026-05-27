package com.Glanzmann100.Projeto_de_Produtos.services;

import com.Glanzmann100.Projeto_de_Produtos.models.User;
import com.Glanzmann100.Projeto_de_Produtos.repositories.UserRepository;
import com.Glanzmann100.Projeto_de_Produtos.services.exceptions.DatabaseException;
import com.Glanzmann100.Projeto_de_Produtos.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    public User insert(User obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) { // para deletar dados ligados ao banco de dados, nesse caso clientes com pedidos
            throw new DatabaseException(e.getMessage()); // chamo a mensagem do DatabaseException
        }
    }
    public User update(Long id, User obj) {
        try {
            User entity = repository.getReferenceById(id); // pega o id salvo pelo RequestBody
            updateData(entity, obj); // copia os novos dados pro entity
            return repository.save(entity); // salva e retorna o entity atualizado
        } catch (EntityNotFoundException e ) {
            throw new ResourceNotFoundException(id);
        }
    }
    private void updateData(User entity, User obj) { // metodo que pega os dados antigos(entity) e os dados novos (obj)
        entity.setName(obj.getName()); // nome antigo pelo novo
        entity.setEmail(obj.getEmail()); // email antigo pelo novo
        entity.setPhone(obj.getPhone()); // phone antigo pelo novo
    }
}
