package com.workshop.course.services;

import com.workshop.course.entities.User;
import com.workshop.course.repositories.UserRepository;
import com.workshop.course.services.exceptions.DatabaseException;
import com.workshop.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por controlar e intermediar a manipulação dos dados entre a base de dados
 * e a classe responsável em controlar - {@link UserRepository} — dos dados do usuário.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    /**
     * Método resposável por controlar e recuperar todos os dados do usuário.
     *
     * @return Retorna todos os dados do usuário.
     */
    public List<User> findAll() {
        return repository.findAll();
    }

    public User findByInd(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * Método responsável em inserir novas informações do usuário na base de dados.
     *
     * @param objeto Recebe os dados do usuário.
     * @return Retorna o status de verdadeiro quando os dados são inseridos com sucesso.
     */
    public User insert(User objeto) {
        return repository.save(objeto);
    }


    /**
     * Método responsável por controlar e deletar os dados de um determinado usuário da base dados.
     *
     * @param id Recebe o código do usuário que deseja apagar da base de dados.
     */
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }


    /**
     * Médoto responsável em controlar e alterar os dados de um determinado usuário.
     *
     * @param id     Recebe o código do usuário que deseja alterar.
     * @param objeto Recebe os dados do usuário que deseja alterar.
     * @return Retorna o status de verdadeiro quando os dados são inseridos com sucesso.
     */
    public User update(Long id, User objeto) {
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, objeto);
            return repository.save(entity);
        } catch (EntityNotFoundException exception) {
            throw new ResourceNotFoundException(id);
        }
    }

    /**
     * Método responsável em controlar e manipular os dados do usuário.
     *
     * @param entity Enviar os dados do usuário alterado para a base de dados.
     * @param objeto Recebe os dados do usuário alterado pelo usuário.
     */
    private void updateData(User entity, User objeto) {
        entity.setName(objeto.getName());
        entity.setEmail(objeto.getEmail());
        entity.setPhone(objeto.getPhone());
    }
}
