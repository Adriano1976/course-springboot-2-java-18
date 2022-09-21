package com.workshop.course.services;

import com.workshop.course.entities.Product;
import com.workshop.course.repositories.ProductRepository;
import com.workshop.course.services.exceptions.DatabaseException;
import com.workshop.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findByInd(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }

    public Product insert(Product objeto) {
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
}
