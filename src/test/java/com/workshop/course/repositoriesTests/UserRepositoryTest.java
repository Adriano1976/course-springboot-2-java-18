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


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


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

    @Test
    public void validaReturnUser() {

        User roberto_santos = new User(1L, "Roberto Santos", "roberto@gmail.com", "7985475874", "123456");

        userRepository.save(roberto_santos);
        userRepository.findAll();
        Assertions.assertTrue(true);
    }

    @Test
    public void validaUpdateUser() {

        User karla_santos = new User(2L, "Karla Santos", "karla@gmail.com", "7985475874", "128456r4875dfe");

        userRepository.save(karla_santos);
        userRepository.getReferenceById(1L);

        updateData(karla_santos, karla_santos = new User(2L, "Karla Santos", "karla.santos@gmail.com", "7985475874", "128456r4875dfe"));
        userRepository.save(karla_santos);
        Assertions.assertTrue(true);
    }

    private void updateData(User entity, User objeto) {
        entity.setName(objeto.getName());
        entity.setEmail(objeto.getEmail());
        entity.setPhone(objeto.getPhone());
    }

    @Test
    public void validaDeleteUser() {

        User paulo_vieira = new User(20L, "Paulo Vieira", "paulo.vieira@gmail.com", "7985475874", "128456r4875dfe");

        userRepository.save(paulo_vieira);
        userRepository.delete(paulo_vieira);
        Assertions.assertTrue(true);
    }


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

    @Test
    @EnabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_17)
    public void validaAlgoSomenteNoUser() {
        Assumptions.assumeFalse("root".equals(System.getenv("User")));
        Assertions.assertEquals(10, 5 + 5);
        Assertions.assertTrue(true);
    }

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
