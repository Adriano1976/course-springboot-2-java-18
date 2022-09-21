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

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findByInd(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Order insert(Order objeto) {
        return repository.save(objeto);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    public Order update(Long id, Order objeto) {
        try {
            Order entity = repository.getReferenceById(id);
            updateData(entity, objeto);
            return repository.save(entity);
        } catch (EntityNotFoundException exception) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Order entity, Order objeto) {
        entity.setMomento(objeto.getMomento());
        entity.setOrderStatus(objeto.getOrderStatus());
        entity.setClient(objeto.getClient());
        entity.setPayment(objeto.getPayment());
    }
}
