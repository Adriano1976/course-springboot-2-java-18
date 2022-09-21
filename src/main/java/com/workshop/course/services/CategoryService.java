package com.workshop.course.services;

import com.workshop.course.entities.Category;
import com.workshop.course.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findByInd(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }

    public Category insert(Category objeto) {
        return repository.save(objeto);
    }
}
