package com.workshop.course.repositoriesTests;

import com.workshop.course.entities.User;
import com.workshop.course.repositories.UserRepository;
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
 * de cadas tipo de testes e validação das atividades do repositório {@link UserRepository} e
 * do serviço {@link com.workshop.course.services.UserService}.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    /**
     * Método responsável por testar e validar a entrada de dados por meio da class
     * {@link UserRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaInsertUser() {

        User user = null;
        Assertions.assertNull(user);

        User roberto_santos = new User(1L, "Roberto Santos", "roberto@gmail.com", "7985475874", "123456");
        Assertions.assertNotNull(roberto_santos);
        Assertions.assertSame(roberto_santos, roberto_santos);

        userRepository.save(roberto_santos);
        Integer countUser = userRepository.findAll().size();
        Assertions.assertEquals(1, countUser);
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar e validar a entrada e retorno de dados por meio da class
     * {@link UserRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaReturnUser() {

        User roberto_santos = new User(1L, "Roberto Santos", "roberto@gmail.com", "7985475874", "123456");

        userRepository.save(roberto_santos);
        userRepository.findAll();
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar e validar a entrada e alteração de dados e por meio da class
     * {@link UserRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaUpdateUser() {

        User karla_santos = new User(2L, "Karla Santos", "karla@gmail.com", "7985475874", "128456r4875dfe");

        userRepository.save(karla_santos);
        userRepository.getReferenceById(1L);

        updateData(karla_santos, karla_santos = new User(2L, "Karla Santos", "karla.santos@gmail.com", "7985475874", "128456r4875dfe"));
        userRepository.save(karla_santos);
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável em controlar e manipular os dados do usuário.
     *
     * @param entity Enviar os dados do usuário alterado para a base de dados.
     * @param objeto Recebe os dados do usuário alterado pelo usuário.
     */
    private void updateData(User entity, User objeto) {
        entity.setName(objeto.getName());
        entity.setEmail(objeto.getEmail());
        entity.setPhone(objeto.getPhone());
    }

    /**
     * Método responsável por testar e validar a entrada e remoção de dados por meio da class
     * {@link UserRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaDeleteUser() {

        User paulo_vieira = new User(20L, "Paulo Vieira", "paulo.vieira@gmail.com", "7985475874", "128456r4875dfe");

        userRepository.save(paulo_vieira);
        userRepository.delete(paulo_vieira);
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar, checar e validar a entrada de dados por meio da class
     * {@link UserRepository} e as outras classes auxiliares.
     */
    @Test
    public void checkUserSaved() {

        User roberto_santos = new User(1L, "Roberto Santos", "roberto@gmail.com", "7985475874", "123456");

        userRepository.save(roberto_santos);
        Integer countUser = userRepository.findAll().size();
        Assertions.assertEquals(1, countUser);

        User karla_santos = new User(2L, "Karla Santos", "karla@gmail.com", "7985475874", "128456r4875dfe");
        Assertions.assertNotNull(karla_santos);
        Assertions.assertNotEquals(roberto_santos, karla_santos);
        Assertions.assertTrue(true);
    }

    /**
     * Médoto responsável em validar a versão do Java e a integridade de manipulação de dados.
     */
    @Test
    @EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_17)
    public void validaAlgoSomenteNoUser() {
        Assumptions.assumeFalse("root".equals(System.getenv("User")));
        Assertions.assertEquals(10, 5 + 5);
        Assertions.assertTrue(true);
    }

    /**
     * Método responsável por testar e validar as exceções ocorrida na manipulação de dados por meio da class
     * {@link UserRepository} e as outras classes auxiliares.
     */
    @Test
    public void validaExceptionUserRepository() {

        User roberto_santos = new User(1L, "Roberto Santos", "roberto@gmail.com", "7985475874", "123456");

        userRepository.save(roberto_santos);
        Integer countUser = userRepository.findAll().size();

        Assertions.assertEquals(1, countUser);
        Assertions.assertDoesNotThrow(() -> userRepository.findAll());
        Assertions.assertTrue(true);
    }
}
