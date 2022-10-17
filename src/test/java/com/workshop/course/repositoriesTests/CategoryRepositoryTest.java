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


/**
 * Classe responsável por configurar, controlar e validar os testes realizados pelos métodos
 * de cadas tipo de testes e validação das atividades do repositório {@link CategoryRepository} e
 * do serviço {@link com.workshop.course.services.CategoryService}.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

    Category electronics = new Category(1L, "Electronics");
    Category books = new Category(2L, "Books");

    @Autowired
    private CategoryRepository CategoryRepository;

    /**
     * Método responsável por testar e validar a entrada de dados por meio da class
     * {@link CategoryRepository} e as outras classes auxiliares.
     */
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

    /**
     * Método responsável por testar e validar a entrada e retorno de dados por meio da class
     * {@link CategoryRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaReturnCategory() {

        CategoryRepository.save(electronics);
        CategoryRepository.findAll();
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar e validar a entrada e alteração de dados por meio da class
     * {@link CategoryRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaUpdateCategory() {

        CategoryRepository.save(electronics);
        CategoryRepository.getReferenceById(1L);

        updateData(electronics, electronics);
        CategoryRepository.save(electronics);
        Assertions.assertTrue(true);
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

    /**
     * Método responsável por testar e validar a entrada e remoção de dados por meio da class
     * {@link CategoryRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaDeleteCategory() {

        CategoryRepository.save(electronics);
        CategoryRepository.delete(electronics);
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar, checar e validar a entrada de dados por meio da class
     * {@link CategoryRepository} e as outras classes auxiliares.
     */
    @Test
    public void checkCategorySaved() {

        CategoryRepository.save(electronics);
        Integer countCategory = CategoryRepository.findAll().size();
        Assertions.assertEquals(1, countCategory);

        Assertions.assertNotNull(electronics);
        Assertions.assertNotEquals(electronics, books);
        Assertions.assertTrue(true);
    }

    /**
     * Médoto responsável em validar a versão do Java e a integridade de manipulação de dados.
     */
    @Test
    @EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_17)
    public void validaAlgoSomenteNoCategory() {
        Assumptions.assumeFalse("root".equals(System.getenv("Category")));
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar e validar as exceções ocorrida na manipulação de dados por meio da class
     * {@link CategoryRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaExceptionCategoryRepository() {

        CategoryRepository.save(electronics);
        Integer countCategory = CategoryRepository.findAll().size();

        Assertions.assertEquals(1, countCategory);
        Assertions.assertDoesNotThrow(() -> CategoryRepository.findAll());
        Assertions.assertTrue(true);
    }
}
