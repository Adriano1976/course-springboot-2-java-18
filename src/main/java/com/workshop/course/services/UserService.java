package com.workshop.course.services;

import com.workshop.course.entities.User;
import com.workshop.course.repositories.UserRepository;
import com.workshop.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findByInd(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User objeto) {
        return repository.save(objeto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User update(Long id, User objeto) {
        User entity = repository.getReferenceById(id);
        updateData(entity, objeto);
        return repository.save(entity);
    }

    private void updateData(User entity, User objeto) {
        entity.setName(objeto.getName());
        entity.setEmail(objeto.getEmail());
        entity.setPhone(objeto.getPhone());
    }
}
