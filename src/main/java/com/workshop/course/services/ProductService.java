package com.workshop.course.services;

import com.workshop.course.entities.Product;
import com.workshop.course.repositories.ProductRepository;
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
 * e a classe responsável em controlar - {@link ProductRepository} — os dados do produto.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    /**
     * Método resposável por controlar e recuperar todos os dados do produto.
     *
     * @return Retorna todos os dados do produto.
     */
    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findByInd(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * Método responsável em inserir novas informações do produto na base de dados.
     *
     * @param objeto Recebe os dados do produto.
     * @return Retorna o status de verdadeiro quando os dados são inseridos com sucesso.
     */
    public Product insert(Product objeto) {
        return repository.save(objeto);
    }

    /**
     * Método responsável por controlar e deletar os dados de um determinado produto da base dados.
     *
     * @param id Recebe o código do produto que deseja apagar da base de dados.
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
     * Médoto responsável em controlar e alterar os dados de um determinado produto.
     *
     * @param id     Recebe o código do produto que deseja alterar.
     * @param objeto Recebe os dados do produto que deseja alterar.
     * @return Retorna o status de verdadeiro quando os dados são inseridos com sucesso.
     */
    public Product update(Long id, Product objeto) {
        try {
            Product entity = repository.getReferenceById(id);
            updateData(entity, objeto);
            return repository.save(entity);
        } catch (EntityNotFoundException exception) {
            throw new ResourceNotFoundException(id);
        }
    }

    /**
     * Método responsável em controlar e manipular os dados do produto.
     *
     * @param entity Enviar os dados do produto alterado para a base de dados.
     * @param objeto Recebe os dados do produto alterado pelo usuário.
     */
    private void updateData(Product entity, Product objeto) {
        entity.setName(objeto.getName());
        entity.setDescription(objeto.getDescription());
        entity.setPrice(objeto.getPrice());
        entity.setImgUrl(objeto.getImgUrl());
    }
}
