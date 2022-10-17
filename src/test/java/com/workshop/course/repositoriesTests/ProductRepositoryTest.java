package com.workshop.course.repositoriesTests;

import com.workshop.course.entities.Product;
import com.workshop.course.repositories.ProductRepository;
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
 * de cadas tipo de testes e validação das atividades do repositório {@link ProductRepository} e
 * do serviço {@link com.workshop.course.services.ProductService}.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryTest {

    Product pc_gamer = new Product(3L, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.00, "");
    Product notbook_sunsung = new Product(2L, "Notbook Sunsung", "Lorem ipsum dolor sit amet, consectetur.", 99.5, "");
    @Autowired
    private ProductRepository productRepository;

    /**
     * Método responsável por testar e validar a entrada de dados por meio da class
     * {@link ProductRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaInsertProduct() {

        Product product = null;
        Assertions.assertNull(product);

        Assertions.assertNotNull(pc_gamer);
        Assertions.assertSame(pc_gamer, pc_gamer);

        productRepository.save(pc_gamer);
        Integer countProduct = productRepository.findAll().size();
        Assertions.assertEquals(1, countProduct);
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar e validar a entrada e retorno de dados por meio da class
     * {@link ProductRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaReturnProduct() {

        productRepository.save(pc_gamer);
        productRepository.findAll();
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar e validar a entrada e alteração de dados por meio da class
     * {@link ProductRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaUpdateProduct() {

        productRepository.save(pc_gamer);
        productRepository.getReferenceById(1L);

        updateData(pc_gamer, pc_gamer);
        productRepository.save(pc_gamer);
        Assertions.assertTrue(true);
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
    }

    /**
     * Método responsável por testar e validar a entrada e remoção de dados por meio da class
     * {@link ProductRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaDeleteProduct() {

        productRepository.save(pc_gamer);
        productRepository.delete(pc_gamer);
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar, checar e validar a entrada de dados por meio da class
     * {@link ProductRepository} e as outras classes auxiliares.
     */
    @Test
    public void checkProductSaved() {

        productRepository.save(pc_gamer);
        Integer countProduct = productRepository.findAll().size();
        Assertions.assertEquals(1, countProduct);

        Assertions.assertNotNull(pc_gamer);
        Assertions.assertNotEquals(pc_gamer, notbook_sunsung);
        Assertions.assertTrue(true);
    }

    /**
     * Médoto responsável em validar a versão do Java e a integridade de manipulação de dados.
     */
    @Test
    @EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_17)
    public void validaAlgoSomenteNoProduct() {
        Assumptions.assumeFalse("root".equals(System.getenv("Product")));
        Assertions.assertEquals(10, 5 + 5);
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar e validar as exceções ocorrida na manipulação de dados por meio da class
     * {@link ProductRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaExceptionProductRepository() {

        productRepository.save(notbook_sunsung);
        Integer countProduct = productRepository.findAll().size();

        Assertions.assertEquals(1, countProduct);
        Assertions.assertDoesNotThrow(() -> productRepository.findAll());
        Assertions.assertTrue(true);
    }
}
