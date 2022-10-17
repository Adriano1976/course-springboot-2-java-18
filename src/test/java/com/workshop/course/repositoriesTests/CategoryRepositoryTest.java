package com.workshop.course.repositoriesTests;

import com.workshop.course.entities.Category;
import com.workshop.course.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

    Category electronics = new Category(1L, "Electronics");
    Category books = new Category(2L, "Books");

    @Autowired
    private CategoryRepository CategoryRepository;

    @Test
    public void validaInsertCategory() {

        Category category = null;
        Assertions.assertNull(category);

        Assertions.assertNotNull(electronics);
        Assertions.assertSame(electronics, electronics);

        CategoryRepository.save(electronics);
        Integer countCategory = CategoryRepository.findAll().size();
        Assertions.assertEquals(1, countCategory);
        Assertions.assertTrue(true);
    }

    @Test
    public void validaReturnCategory() {

        CategoryRepository.save(electronics);
        CategoryRepository.findAll();
        Assertions.assertTrue(true);
    }

    @Test
    public void validaUpdateCategory() {

        CategoryRepository.save(electronics);
        CategoryRepository.getReferenceById(1L);

        updateData(electronics, electronics);
        CategoryRepository.save(electronics);
        Assertions.assertTrue(true);
    }

    private void updateData(Category entity, Category objeto) {
        entity.setname(objeto.getname());
    }

    @Test
    public void validaDeleteCategory() {

        CategoryRepository.save(electronics);
        CategoryRepository.delete(electronics);
        Assertions.assertTrue(true);
    }


    @Test
    public void checkCategorySaved() {

        CategoryRepository.save(electronics);
        Integer countCategory = CategoryRepository.findAll().size();
        Assertions.assertEquals(1, countCategory);

        Assertions.assertNotNull(electronics);
        Assertions.assertNotEquals(electronics, books);
        Assertions.assertTrue(true);
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_17)
    public void validaAlgoSomenteNoCategory() {
        Assumptions.assumeFalse("root".equals(System.getenv("Category")));
        Assertions.assertTrue(true);
    }

    @Test
    public void validaExceptionCategoryRepository() {

        CategoryRepository.save(electronics);
        Integer countCategory = CategoryRepository.findAll().size();

        Assertions.assertEquals(1, countCategory);
        Assertions.assertDoesNotThrow(() -> CategoryRepository.findAll());
        Assertions.assertTrue(true);
    }
}
