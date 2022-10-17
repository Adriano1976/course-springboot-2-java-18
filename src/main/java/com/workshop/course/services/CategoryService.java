package com.workshop.course.services;

import com.workshop.course.entities.Category;
import com.workshop.course.repositories.CategoryRepository;
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
 * e a classe responsável em controlar - {@link CategoryRepository} — os dados da categoria do produto.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    /**
     * Método resposável por controlar e recuperar todos os dados da categoria do produto.
     *
     * @return Retorna todos os dados da catoria do produto.
     */
    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findByInd(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * Método responsável em inserir novas informações da categoria do produto na base de dados.
     *
     * @param objeto Recebe os dados da categoria do produto.
     * @return Retorna o status de verdadeiro quando os dados são inseridos com sucesso.
     */
    public Category insert(Category objeto) {
        return repository.save(objeto);
    }

    /**
     * Método responsável por controlar e deletar os dados de uma determinada categoria da base dados.
     *
     * @param id Recebe o código da categoria que deseja apagar da base de dados.
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

    public Category update(Long id, Category objeto) {
        try {
            Category entity = repository.getReferenceById(id);
            updateData(entity, objeto);
            return repository.save(entity);
        } catch (EntityNotFoundException exception) {
            throw new ResourceNotFoundException(id);
        }
    }

    /**
     * Método responsável em controlar e manipular os dados da categoria do produto.
     *
     * @param entity Enviar os dados da categoria do produto alterado para a base de dados.
     * @param objeto Recebe os dados da categoria do produto alterado pelo usuário.
     */
    private void updateData(Category entity, Category objeto) {
        entity.setname(objeto.getname());
    }
}
