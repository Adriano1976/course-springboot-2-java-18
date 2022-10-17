package com.workshop.course.entitiesTests;

import com.workshop.course.entities.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Classe responsável por configurar, controlar e validar os testes realizados pelos métodos
 * de cadas tipo de testes e validação da entrada de dados na entidade {@link Category}.
 */
public class CategoryTest {

    @Test
    public void CategoryTests() {

        Category electronics = new Category(1L, "Electronics");
        Category books = new Category(2L, "Books");
        Category computers = new Category(3L, "Computers");

        Assertions.assertEquals("Electronics", electronics.getname());

        Assertions.assertNotEquals(books.getId(), computers.getId());

        Assertions.assertNotNull(electronics.getId());
        Assertions.assertNotNull(electronics.getProducts());
        Assertions.assertNotNull(electronics.getId(), String.valueOf(books.getId()));

        Assertions.assertFalse(computers.toString().contains("Category{"));

    }
}
