package com.workshop.course.services;

import com.workshop.course.entities.Order;
import com.workshop.course.repositories.OrderRepository;
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
 * e a classe responsável em controlar - {@link OrderRepository} — os dados da ordem de compro do produto.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    /**
     * Método resposável por controlar e recuperar todos os dados da ordem de compra.
     *
     * @return Retorna todos os dados da ordem de compra.
     */
    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findByInd(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    /**
     * Método responsável em inserir novas informações da ordem de compra na base de dados.
     *
     * @param objeto Recebe os dados da ordem de compra.
     * @return Retorna o status de verdadeiro quando os dados são inseridos com sucesso.
     */
    public Order insert(Order objeto) {
        return repository.save(objeto);
    }

    /**
     * Método responsável por controlar e deletar os dados de uma determinada ordem de compra da base dados.
     *
     * @param id Recebe o código da ordem de compra que deseja apagar da base de dados.
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
     * Médoto responsável em controlar e alterar os dados de uma determinado ordem de compra.
     *
     * @param id     Recebe o código da ordem de compra que deseja alterar.
     * @param objeto Recebe os dados da ordem de compra que deseja alterar.
     * @return Retorna o status de verdadeiro quando os dados são inseridos com sucesso.
     */
    public Order update(Long id, Order objeto) {
        try {
            Order entity = repository.getReferenceById(id);
            updateData(entity, objeto);
            return repository.save(entity);
        } catch (EntityNotFoundException exception) {
            throw new ResourceNotFoundException(id);
        }
    }

    /**
     * Método responsável em controlar e manipular os dados da ordem de compra.
     *
     * @param entity Enviar os dados da ordem de compra alterado para a base de dados.
     * @param objeto Recebe os dados da ordem de compra alterado pelo usuário.
     */
    private void updateData(Order entity, Order objeto) {
        entity.setMomento(objeto.getMomento());
        entity.setOrderStatus(objeto.getOrderStatus());
        entity.setClient(objeto.getClient());
        entity.setPayment(objeto.getPayment());
    }
}
