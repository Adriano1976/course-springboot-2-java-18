package com.workshop.course.entitiesTests;

import com.workshop.course.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Classe responsável por configurar, controlar e validar os testes realizados pelos métodos
 * de cadas tipo de testes e validação da entrada de dados na entidade {@link User}.
 */
public class UserTest {

    /**
     * Método responsável por testar e validar a entrada de dados da classe {@link User}.
     */
    @Test
    public void UserTests() {

        User roberto_santos = new User(1L, "Roberto Santos", "roberto@gmail.com", "7985475874", "123456");
        User karla_santos = new User(2L, "Karla Santos", "karla@gmail.com", "7985475874", "128456r4875dfe");

        Assertions.assertEquals("Roberto Santos", roberto_santos.getName());
        Assertions.assertEquals("roberto@gmail.com", roberto_santos.getEmail());
        Assertions.assertEquals("7985475874", roberto_santos.getPhone());
        Assertions.assertEquals("123456", roberto_santos.getPassword());

        Assertions.assertNotEquals(roberto_santos.getPassword(), karla_santos.getPassword());
        Assertions.assertNotNull(roberto_santos.getId(), String.valueOf(karla_santos.getId()));

        Assertions.assertFalse(roberto_santos.toString().contains("User{"));

    }
}
